package com.corona.insights.service;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.dao.CountryWiseDaoImpl;
import com.corona.insights.dao.LocationDaoImpl;
import com.corona.insights.dao.PropertiesDaoImpl;
import com.corona.insights.etl.CoronaInsightsETLProcessor;
import com.corona.insights.jooq.corona_insights.tables.pojos.Location;
import com.corona.insights.jooq.corona_insights.tables.pojos.Properties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
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
    private PropertiesDaoImpl propertiesDao;

    public void process() {

        List<String> countryList = locationDao.getUniqueCountryList();

        Timestamp cutOffDate = getCutOffDate();

        for (String country : countryList) {
            CoronaInsightsETLProcessor coronaInsightsETLProcessor = new CoronaInsightsETLProcessor(casesDao, countryWiseDao, country, cutOffDate);
            coronaInsightsETLProcessor.extract();
            coronaInsightsETLProcessor.transform();
            coronaInsightsETLProcessor.load();
        }

        updateCutOffDate();

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
