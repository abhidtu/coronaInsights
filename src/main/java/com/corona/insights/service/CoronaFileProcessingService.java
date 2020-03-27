package com.corona.insights.service;

import com.corona.insights.dao.CasesDaoImpl;
import com.corona.insights.dao.LocationDaoImpl;
import com.corona.insights.jooq.corona_insights.tables.pojos.Cases;
import com.corona.insights.jooq.corona_insights.tables.pojos.Location;
import com.corona.insights.model.CoronaVirusReportDataModel;
import com.corona.insights.parser.csv.CoronaVirusFileParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Date;
import java.util.List;

@Slf4j
@Component
public class CoronaFileProcessingService {

    private CasesDaoImpl casesDao;
    private LocationDaoImpl locationDao;
    private CoronaVirusFileParser<CoronaVirusReportDataModel> coronaVirusFileParser;

    public CoronaFileProcessingService(CasesDaoImpl casesDao, LocationDaoImpl locationDao, CoronaVirusFileParser<CoronaVirusReportDataModel> coronaVirusFileParser) {
        this.casesDao = casesDao;
        this.locationDao = locationDao;
        this.coronaVirusFileParser = coronaVirusFileParser;
    }

    public void processFile(File file) {
            log.info("processing file = {}", file.getName());
            List<CoronaVirusReportDataModel> coronaVirusReportDataModels = coronaVirusFileParser.parse(file);
            coronaVirusReportDataModels.forEach(coronaVirusReportDataModel -> {
                try {
                    log.info("Started processing CoronaVirusReportModel file for state = {}", coronaVirusReportDataModel.getState());
                    process(coronaVirusReportDataModel, file.getName());
                    log.info("Successfully processed the CoronaVirus Report model = {}", coronaVirusReportDataModel.getState());
                } catch (Exception e) {
                    log.error("Error parsing the record = {}", coronaVirusReportDataModel);
                }
            });
    }

    private void process(CoronaVirusReportDataModel coronaVirusReportDataModel, String fileName) {
        Location location = new Location();
        location.setCountry(coronaVirusReportDataModel.getCountry());
        location.setState(coronaVirusReportDataModel.getState());
        location.setLatitude(coronaVirusReportDataModel.getLatitude());
        location.setLongitude(coronaVirusReportDataModel.getLongitude());
        location.setFileName(fileName);
        log.info("saving location data");
        Integer locationId = locationDao.createOrUpdate(location);

        Cases cases = new Cases();
        cases.setLocationId(locationId);
        cases.setReportingDate(Date.valueOf(coronaVirusReportDataModel.getReportedDate()));
        cases.setConfirmed(coronaVirusReportDataModel.getConfirmed());
        cases.setDeaths(coronaVirusReportDataModel.getDeaths());
        cases.setRecovered(coronaVirusReportDataModel.getRecovered());
        cases.setReportingTimestamp(coronaVirusReportDataModel.getReportedTimestamp());
        cases.setFileName(fileName);
        log.info("Saving the case data");
        casesDao.createOrUpdate(cases);
    }

    public boolean isNotProcessed(String fileName) {
        return casesDao.fetchByFileName(fileName).isEmpty();
    }

}