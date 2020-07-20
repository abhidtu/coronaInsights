package com.corona.insights.etl;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.dao.CountryWiseDaoImpl;
import com.corona.insights.jooq.corona_insights.enums.CountryWiseSource;
import com.corona.insights.jooq.corona_insights.tables.pojos.CountryWise;
import com.corona.insights.model.CoronaVirusETLMetricsDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CoronaInsightsCountriesETLProcessor implements ETLProcessor {

    private String country;
    private Timestamp cutOffDate;
    private CasesDaoImpl casesDao;
    private CountryWiseDaoImpl countryWiseDao;
    private List<CoronaVirusETLMetricsDTO> coronaVirusETLMetricsDTOList;
    private List<CountryWise> countryWiseList;

    public CoronaInsightsCountriesETLProcessor(CasesDaoImpl casesDao, CountryWiseDaoImpl countryWiseDao, String country, Timestamp cutOffDate) {
        this.casesDao = casesDao;
        this.countryWiseDao = countryWiseDao;
        this.country = country;
        this.cutOffDate = cutOffDate;
        this.countryWiseList = new ArrayList<>();
    }

    @Override
    public void extract() {
        log.info("Step 1: executing Extract for country = {}", country);
        coronaVirusETLMetricsDTOList = cutOffDate == null ? casesDao.aggregateDataForCountry(country) : casesDao.aggregateDataForCountryWithCutOffDate(country, cutOffDate);
    }

    @Override
    public void transform() {
        log.info("Step 2: executing Transform for country = {}", country);
        CoronaVirusETLMetricsDTO previousCoronaVirusETLMetricsDTO = new CoronaVirusETLMetricsDTO();
        for (CoronaVirusETLMetricsDTO coronaVirusETLMetricsDTO : coronaVirusETLMetricsDTOList) {
            CountryWise countryWise = new CountryWise();
            countryWise.setCountry(country);

            countryWise.setConfirmed(coronaVirusETLMetricsDTO.getConfirmed());
            countryWise.setDeaths(coronaVirusETLMetricsDTO.getDeaths());
            countryWise.setRecovered(coronaVirusETLMetricsDTO.getRecovered());
            countryWise.setActive(coronaVirusETLMetricsDTO.getConfirmed() - coronaVirusETLMetricsDTO.getDeaths() - coronaVirusETLMetricsDTO.getRecovered());

            coronaVirusETLMetricsDTO.computeDelta(previousCoronaVirusETLMetricsDTO);

            countryWise.setDeltaConfirmed(coronaVirusETLMetricsDTO.getDeltaConfirmed());
            countryWise.setDeltaDeaths(coronaVirusETLMetricsDTO.getDeltaDeaths());
            countryWise.setDeltaRecovered(coronaVirusETLMetricsDTO.getDeltaRecovered());
            countryWise.setDeltaActive(coronaVirusETLMetricsDTO.getDeltaActive());

            countryWise.setReportingDate(coronaVirusETLMetricsDTO.getReportedDate());
            countryWise.setSource(CountryWiseSource.JHU);
            countryWiseList.add(countryWise);

            previousCoronaVirusETLMetricsDTO = coronaVirusETLMetricsDTO;

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
