package com.corona.insights.service;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.jooq.corona_insights.tables.pojos.Cases;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CoronaDataEnrichmentService {

    private CasesDaoImpl casesDao;

    public CoronaDataEnrichmentService(CasesDaoImpl casesDao) {
        this.casesDao = casesDao;
    }

    public void enrich() {

        List<Date> reportingDates = casesDao.getDistinctReportingDates();

        List<Integer> firstSet = new ArrayList<>();
        Date firstReportingDate = null;
        List<Integer> secondSet;
        Date secondReportingDate;
        for (Date reportingDate : reportingDates) {
            secondSet = casesDao.getLocationIdsForReportingDate(reportingDate);
            secondReportingDate = reportingDate;
            intersectData(firstSet, secondSet);
            processMissingData(firstReportingDate, secondReportingDate, firstSet);
            firstSet = casesDao.getLocationIdsForReportingDate(reportingDate);
            firstReportingDate = secondReportingDate;
        }

    }

    private void intersectData(List<Integer> list1, List<Integer> list2) {
        list1.removeAll(list2);
    }

    private void processMissingData(Date firstReportingDate, Date secondReportingDate, List<Integer> locationIds) {

        for (Integer locationId : locationIds) {
            Cases cases = casesDao.fetchCaseByReportingDateAndLocationId(firstReportingDate, locationId);
            Cases newCase = new Cases();
            newCase.setReportingDate(secondReportingDate);
            newCase.setLocationId(locationId);
            newCase.setFileName(cases.getFileName());
            newCase.setConfirmed(cases.getConfirmed());
            newCase.setDeaths(cases.getDeaths());
            newCase.setRecovered(cases.getRecovered());
            newCase.setReportingTimestamp(new Timestamp(secondReportingDate.getTime()));
            casesDao.createOrUpdate(newCase);
            log.info("Saving the case = {}", newCase);
        }

    }

}