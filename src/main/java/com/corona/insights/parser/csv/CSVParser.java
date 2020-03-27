package com.corona.insights.parser.csv;

import de.siegmar.fastcsv.reader.CsvRow;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CSVParser {

    public List<CsvRow> parseCSV(File file) throws IOException;

}
