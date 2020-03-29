package com.corona.insights.etl;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.model.CoronaVirusCountryWiseData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CoronaInsightsETLProcessor implements ETLProcessor {

    private String country;
    private CasesDaoImpl casesDao;

    public CoronaInsightsETLProcessor(CasesDaoImpl casesDao) {
        this.casesDao = casesDao;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public void extract() {
        List<CoronaVirusCountryWiseData> coronaVirusCountryWiseData = casesDao.aggregateDataForCountry(country);
        int t=0;
    }

    @Override
    public void transform() {

    }

    @Override
    public void load() {

    }

}
