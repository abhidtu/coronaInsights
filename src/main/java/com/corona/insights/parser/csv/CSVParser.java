package com.corona.insights.parser.csv;

import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CSVParser {

    public List<CSVRecord> parseCSV(File file) throws IOException;

}
