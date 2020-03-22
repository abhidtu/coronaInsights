package com.corona.insights.parser.csv;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Slf4j
@Component
public class CSVParserImpl implements CSVParser {

    private CSVFormat csvFormat;

    @Override
    public List<CSVRecord> parseCSV(File file) throws IOException {
        return readData(file);
    }

    private List<CSVRecord> readData(File file) throws IOException {
        InputStream csvFile = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(csvFile);
        if(csvFormat == null) {
            synchronized (this){
                if (csvFormat == null) {
                    buildCSVFormatObject();
                }
            }
        }
        return org.apache.commons.csv.CSVParser.parse(input, csvFormat).getRecords();
    }

    private void buildCSVFormatObject() throws IOException {
        CSVFormat csvFormat = CSVFormat.RFC4180.withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces()
                .withNullString("");
        this.csvFormat = csvFormat.withFirstRecordAsHeader();
    }

}
