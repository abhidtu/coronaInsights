package com.corona.insights.parser.csv;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CSVParserImpl implements CSVParser {

    private CsvReader csvReader = new CsvReader();

    @Override
    public List<CsvRow> parseCSV(File file) throws IOException {
        return readData(file);
    }

    private List<CsvRow> readData(File file) throws IOException {
        csvReader.parse(file, StandardCharsets.UTF_8);

        CsvReader csvReader = new CsvReader();
        csvReader.setContainsHeader(true);

        CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);

        return csv.getRows();
    }


}
