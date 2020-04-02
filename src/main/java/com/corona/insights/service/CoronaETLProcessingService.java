package com.corona.insights.service;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.dao.CountryWiseDaoImpl;
import com.corona.insights.dao.LocationDaoImpl;
import com.corona.insights.etl.CoronaInsightsETLProcessor;
import com.corona.insights.jooq.corona_insights.CoronaInsights;
import com.corona.insights.jooq.corona_insights.tables.daos.CasesDao;
import com.corona.insights.jooq.corona_insights.tables.daos.LocationDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.Cases;
import com.corona.insights.jooq.corona_insights.tables.pojos.Location;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CoronaETLProcessingService {

    private LocationDaoImpl locationDao;
    private CasesDaoImpl casesDao;
    private CountryWiseDaoImpl countryWiseDao;

    public void process() {

        List<Location> locations = locationDao.findAll();

        for (Location location : locations) {
            CoronaInsightsETLProcessor coronaInsightsETLProcessor = new CoronaInsightsETLProcessor(casesDao, countryWiseDao, location.getCountry());
            coronaInsightsETLProcessor.extract();
            coronaInsightsETLProcessor.transform();
            coronaInsightsETLProcessor.load();
        }

    }

}
