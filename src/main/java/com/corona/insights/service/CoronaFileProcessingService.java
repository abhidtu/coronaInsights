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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
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
                //log.info("Started processing CoronaVirusReportModel file for state = {}", coronaVirusReportDataModel.toString());
                process(coronaVirusReportDataModel, file.getName());
                //log.info("Successfully processed the CoronaVirus Report model = {}", coronaVirusReportDataModel.getState());
            } catch (Exception e) {
                log.error("Error processing the record = {}", coronaVirusReportDataModel.toString());
                e.printStackTrace();
            }
        });
    }

    private void process(CoronaVirusReportDataModel coronaVirusReportDataModel, String fileName) {
        Location location = new Location();
        location.setCountry(restructureCountry(coronaVirusReportDataModel.getCountry()));
        location.setState(coronaVirusReportDataModel.getState());
        location.setDistrict(coronaVirusReportDataModel.getDistrict());
        location.setLatitude(trimLatLong(coronaVirusReportDataModel.getLatitude()));
        location.setLongitude(trimLatLong(coronaVirusReportDataModel.getLongitude()));
        location.setFileName(fileName);
        //log.info("saving location data");
        Integer locationId = locationDao.createOrUpdate(location);

        Date reportingDate = Date.valueOf(coronaVirusReportDataModel.getReportedDate());

        Cases existingCases = casesDao.getCase(reportingDate, locationId);
        if(existingCases != null) {
            existingCases.setConfirmed(coronaVirusReportDataModel.getConfirmed());
            existingCases.setDeaths(coronaVirusReportDataModel.getDeaths());
            existingCases.setRecovered(coronaVirusReportDataModel.getRecovered());
            existingCases.setReportingTimestamp(coronaVirusReportDataModel.getReportedTimestamp());
            existingCases.setFileName(fileName);
            //log.info("Saving the case data");
            casesDao.update(existingCases);
        }else {
            Cases cases = new Cases();
            cases.setLocationId(locationId);
            cases.setReportingDate(reportingDate);
            cases.setConfirmed(coronaVirusReportDataModel.getConfirmed());
            cases.setDeaths(coronaVirusReportDataModel.getDeaths());
            cases.setRecovered(coronaVirusReportDataModel.getRecovered());
            cases.setReportingTimestamp(coronaVirusReportDataModel.getReportedTimestamp());
            cases.setFileName(fileName);
            //log.info("Saving the case data");
            casesDao.createOrUpdate(cases);
        }
    }



    public List<File> getNewFilesToProcess(List<File> files) {
        List<File> newFiles = new ArrayList<>();
        files.forEach(file -> {
            if(isNotProcessed(file.getName())) newFiles.add(file);
        });
        return newFiles;
    }

    private boolean isNotProcessed(String fileName) {
        log.info("Checking if {} is already processed", fileName);
        return casesDao.fetchByFileName(fileName).isEmpty();
    }

    private BigDecimal trimLatLong(BigDecimal latLong) {
        if(latLong != null) {
            latLong = latLong.setScale(6, RoundingMode.DOWN);
        }
        return latLong;
    }

    private String restructureCountry(String country) {
        if (country.equals("Mainland China")) {
            country = "China";
        }else if(country.equals("UK")) {
            country = "United Kingdom";
        }
        return country;
    }

}