package com.corona.insights.fileparser;

import com.corona.insights.model.CoronaVirusReportDataModel;
import com.corona.insights.parser.csv.CSVParser;
import com.corona.insights.parser.csv.CoronaReportCSVHeaders;
import com.corona.insights.parser.csv.CoronaVirusFileParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JohnHopkinsCoronaVirusInsightsCSVParser implements CoronaVirusFileParser<CoronaVirusReportDataModel> {

    private CSVParser csvParser;

    private final String DATE_FORMAT = "yyyyMMddHHmm";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public JohnHopkinsCoronaVirusInsightsCSVParser(CSVParser csvParser) {
        this.csvParser = csvParser;
    }

    @Override
    public List<CoronaVirusReportDataModel> parse(File file) {
        List<CSVRecord> csvRecords = new ArrayList<>();
        try {
            log.info("Parsing csv file = {}", file.getName());
            csvRecords = csvParser.parseCSV(file);
            log.info("Successfully parsed CSV file = {}", file.getName());
        }catch (Exception e) {
            log.error("Exception parsing the file = {}", file.getName());
        }
        return mappedRecord(csvRecords);
    }

    @Override
    public List<CoronaVirusReportDataModel> mappedRecord(List<CSVRecord> csvRecords) {
        List<CoronaVirusReportDataModel> coronaVirusReportDataModels = new ArrayList<>();
        csvRecords.forEach(csvRecord -> {
            CoronaVirusReportDataModel coronaVirusReportDataModel = new CoronaVirusReportDataModel();

            coronaVirusReportDataModel.setCountry(csvRecord.get(CoronaReportCSVHeaders.COUNTRY_REGION));
            coronaVirusReportDataModel.setState(csvRecord.get(CoronaReportCSVHeaders.STATE_PROVINCE));
            //coronaVirusReportDataModel.setLatitude(new BigDecimal(csvRecord.get(CoronaReportCSVHeaders.LATITUDE)));
            //coronaVirusReportDataModel.setLongitude(new BigDecimal(csvRecord.get(CoronaReportCSVHeaders.LONGITUDE)));
            coronaVirusReportDataModel.setReportedDate(buildDateTime(csvRecord.get(CoronaReportCSVHeaders.LAST_UPDATE)));
            coronaVirusReportDataModel.setConfirmed(Long.valueOf(csvRecord.get(CoronaReportCSVHeaders.CONFIRMED)));
            coronaVirusReportDataModel.setDeaths(Integer.valueOf(csvRecord.get(CoronaReportCSVHeaders.DEATHS)));
            coronaVirusReportDataModel.setRecovered(Long.valueOf(csvRecord.get(CoronaReportCSVHeaders.RECOVERED)));

            coronaVirusReportDataModels.add(coronaVirusReportDataModel);

        });
        return coronaVirusReportDataModels;
    }

    private Timestamp buildDateTime(String date) {
        Timestamp dateTime = null;
        if(date != null) {
            try {
                dateTime = new Timestamp(dateFormat.parse(date).getTime());
            } catch (ParseException e) {
                log.error("Unable to parse date = {}", date);
            }
        }
        return dateTime;
    }

}
