package com.corona.insights.fileparser;

import com.corona.insights.model.CoronaVirusReportDataModel;
import com.corona.insights.parser.csv.CSVParser;
import com.corona.insights.parser.csv.CoronaInsightsCSVRowAttributeBuilder;
import com.corona.insights.parser.csv.CoronaVirusFileParser;
import de.siegmar.fastcsv.reader.CsvRow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JohnHopkinsCoronaVirusInsightsCSVParser implements CoronaVirusFileParser<CoronaVirusReportDataModel> {

    private CSVParser csvParser;

    public JohnHopkinsCoronaVirusInsightsCSVParser(CSVParser csvParser) {
        this.csvParser = csvParser;
    }

    @Override
    public List<CoronaVirusReportDataModel> parse(File file) {
        List<CsvRow> csvRecords = new ArrayList<>();
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
    public List<CoronaVirusReportDataModel> mappedRecord(List<CsvRow> csvRecords) {
        List<CoronaVirusReportDataModel> coronaVirusReportDataModels = new ArrayList<>();
        for(CsvRow csvRecord : csvRecords) {
            CoronaInsightsCSVRowAttributeBuilder coronaInsightsCSVRowAttributeBuilder = new CoronaInsightsCSVRowAttributeBuilder(csvRecord);
            CoronaVirusReportDataModel coronaVirusReportDataModel = new CoronaVirusReportDataModel();
            coronaVirusReportDataModel.setCountry(coronaInsightsCSVRowAttributeBuilder.getCountry());
            coronaVirusReportDataModel.setState(coronaInsightsCSVRowAttributeBuilder.getState());
            coronaVirusReportDataModel.setLatitude(coronaInsightsCSVRowAttributeBuilder.getLatitude());
            coronaVirusReportDataModel.setLongitude(coronaInsightsCSVRowAttributeBuilder.getLongitude());
            coronaVirusReportDataModel.setReportedDate(coronaInsightsCSVRowAttributeBuilder.getLastUpdateDate());
            coronaVirusReportDataModel.setReportedTimestamp(coronaInsightsCSVRowAttributeBuilder.getLastUpdateTimestamp());
            coronaVirusReportDataModel.setConfirmed(coronaInsightsCSVRowAttributeBuilder.getConfirmed());
            coronaVirusReportDataModel.setDeaths(coronaInsightsCSVRowAttributeBuilder.getDeaths());
            coronaVirusReportDataModel.setRecovered(coronaInsightsCSVRowAttributeBuilder.getRecovered());
            coronaVirusReportDataModels.add(coronaVirusReportDataModel);
        }
        return coronaVirusReportDataModels;
    }

}