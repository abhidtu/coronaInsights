package com.corona.insights.etl;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.dao.CountryWiseDaoImpl;
import com.corona.insights.jooq.corona_insights.tables.pojos.CountryWise;
import com.corona.insights.model.CoronaVirusCountryWiseData;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CoronaInsightsETLProcessor implements ETLProcessor {

    private String country;
    private Timestamp cutOffDate;
    private CasesDaoImpl casesDao;
    private CountryWiseDaoImpl countryWiseDao;
    private List<CoronaVirusCountryWiseData> coronaVirusCountryWiseDataList;
    private List<CountryWise> countryWiseList;

    public CoronaInsightsETLProcessor(CasesDaoImpl casesDao, CountryWiseDaoImpl countryWiseDao, String country, Timestamp cutOffDate) {
        this.casesDao = casesDao;
        this.countryWiseDao = countryWiseDao;
        this.country = country;
        this.cutOffDate = cutOffDate;
        this.countryWiseList = new ArrayList<>();
    }

    @Override
    public void extract() {
        log.info("Step 1: executing Extract");
        coronaVirusCountryWiseDataList = cutOffDate == null ? casesDao.aggregateDataForCountry(country) : casesDao.aggregateDataForCountryWithCutOffDate(country, cutOffDate);
    }

    @Override
    public void transform() {
        log.info("Step 2: executing Transform");
        for (CoronaVirusCountryWiseData coronaVirusCountryWiseData : coronaVirusCountryWiseDataList) {
            CountryWise countryWise = new CountryWise();
            countryWise.setConfirmed(coronaVirusCountryWiseData.getConfirmed());
            countryWise.setCountry(country);
            countryWise.setDeaths(coronaVirusCountryWiseData.getDeaths());
            countryWise.setRecovered(coronaVirusCountryWiseData.getRecovered());
            countryWise.setReportingDate(coronaVirusCountryWiseData.getReportedDate());
            countryWiseList.add(countryWise);
        }
    }

    @Override
    public void load() {
        log.info("Step 3: executing Load");
        for (CountryWise countryWise : countryWiseList) {
            try {
                countryWiseDao.insert(countryWise);
                log.info("Inserting country wise data for country = {}, value = {}", country, countryWise);
            }catch (Exception e) {
                log.error("Exception inserting country wise data for country = {}, value = {}", country, countryWise);
            }
        }
    }

}
