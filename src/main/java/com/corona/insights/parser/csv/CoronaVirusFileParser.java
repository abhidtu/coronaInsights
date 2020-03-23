package com.corona.insights.parser.csv;

import de.siegmar.fastcsv.reader.CsvRow;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.List;

public interface CoronaVirusFileParser<R> {

    List<R> parse(File file);

    List<R>  mappedRecord(List<CsvRow> csvRecords);

}
