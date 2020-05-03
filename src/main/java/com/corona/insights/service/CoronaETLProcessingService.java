package com.corona.insights.service;

import com.corona.insights.dao.*;
import com.corona.insights.etl.CoronaInsightsCountriesETLProcessor;
import com.corona.insights.etl.CoronaInsightsDistrictETLProcessor;
import com.corona.insights.etl.CoronaInsightsStateETLProcessor;
import com.corona.insights.jooq.corona_insights.tables.pojos.Location;
import com.corona.insights.jooq.corona_insights.tables.pojos.Properties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.corona.insights.jooq.corona_insights.tables.Properties.PROPERTIES;

@Slf4j
@Component
@AllArgsConstructor
public class CoronaETLProcessingService {

    private static String LAST_RECORD_PROCESSED_IN_ETL_CREATED_TIME = "LAST_RECORD_PROCESSED_IN_ETL_CREATED_TIME";
    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private LocationDaoImpl locationDao;
    private CasesDaoImpl casesDao;
    private CountryWiseDaoImpl countryWiseDao;
    private StateWiseImplDao stateWiseDao;
    private DistrictWiseDaoImpl districtWiseDao;
    private PropertiesDaoImpl propertiesDao;
    private DaoUtils daoUtils;

    public void process() {
        daoUtils.removeOtherSourcesData();
        if(!daoUtils.isUpdateLocked()) {
            daoUtils.acquireUpdateLock();
            List<String> countryList = locationDao.getUniqueCountryList();

            Timestamp cutOffDate = getCutOffDate();

            for (String country : countryList) {
                processCountriesData(country, cutOffDate);
                List<String> states = locationDao.getUniqueStatesListForCountry(country);
                for (String state : states) {
                    processStatesData(country, state, cutOffDate);
                    List<Location> locations = locationDao.getUniqueLocationsForDistrict(state);
                    for (Location location : locations) {
                        processDistrictData(location, cutOffDate);
                    }
                }
            }
            updateCutOffDate();
            daoUtils.releaseUpdateLock();
        }
    }

    private void processCountriesData(String country, Timestamp cutOffDate) {
        CoronaInsightsCountriesETLProcessor coronaInsightsCountriesETLProcessor = new CoronaInsightsCountriesETLProcessor(casesDao, countryWiseDao, country, cutOffDate);
        coronaInsightsCountriesETLProcessor.extract();
        coronaInsightsCountriesETLProcessor.transform();
        coronaInsightsCountriesETLProcessor.load();
    }

    private void processStatesData(String country, String state, Timestamp cutOffDate) {
        CoronaInsightsStateETLProcessor coronaInsightsStateETLProcessor = new CoronaInsightsStateETLProcessor(country, state, cutOffDate, casesDao, stateWiseDao);
        coronaInsightsStateETLProcessor.extract();
        coronaInsightsStateETLProcessor.transform();
        coronaInsightsStateETLProcessor.load();
    }

    private void processDistrictData(Location location, Timestamp cutOffDate) {
        CoronaInsightsDistrictETLProcessor coronaInsightsDistrictETLProcessor = new CoronaInsightsDistrictETLProcessor(location, cutOffDate, casesDao, districtWiseDao);
        coronaInsightsDistrictETLProcessor.extract();
        coronaInsightsDistrictETLProcessor.transform();
        coronaInsightsDistrictETLProcessor.load();
    }

    private Timestamp getCutOffDate() {
        Properties properties = propertiesDao.fetchOne(PROPERTIES.KEY, LAST_RECORD_PROCESSED_IN_ETL_CREATED_TIME);
        try {
            log.info("Fetching the cutOfDate from the db");
            return properties.getValue() != null ? new Timestamp(dateFormat.parse(properties.getValue()).getTime()) : null;
        } catch (ParseException e) {
            log.error("Error parsing the date");
        }
        return null;
    }

    private void updateCutOffDate() {
        log.info("Computing the cut off date from db");
        Timestamp cutOffDate = casesDao.computeCutOfDate();
        if(cutOffDate != null) {
            log.info("Fetching the cut off date from the db");
            Properties properties = propertiesDao.fetchOne(PROPERTIES.KEY, LAST_RECORD_PROCESSED_IN_ETL_CREATED_TIME);
            properties.setValue(cutOffDate.toString());
            propertiesDao.update(properties);
        }
    }

}
