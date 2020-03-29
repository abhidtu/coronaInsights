package com.corona.insights.service;

import com.corona.insights.etl.CoronaInsightsETLProcessor;
import com.corona.insights.jooq.corona_insights.CoronaInsights;
import com.corona.insights.jooq.corona_insights.tables.daos.CasesDao;
import com.corona.insights.jooq.corona_insights.tables.daos.LocationDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.Cases;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CoronaETLProcessingService {

    private CoronaInsightsETLProcessor coronaInsightsETLProcessor;
    private CasesDao casesDao;
    private LocationDao locationDao;

    public void process() {

        coronaInsightsETLProcessor.setCountry("US");
        coronaInsightsETLProcessor.extract();
        coronaInsightsETLProcessor.transform();
        coronaInsightsETLProcessor.load();

    }

}
