package com.corona.insights.fileparser;

import com.corona.insights.model.CoronaVirusReportDataModel;
import com.corona.insights.parser.csv.CSVParser;
import com.corona.insights.parser.csv.CoronaReportCSVHeaders;
import com.corona.insights.parser.csv.CoronaVirusFileParser;
import de.siegmar.fastcsv.reader.CsvRow;
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

    private final String ALT_DATE_FORMAT = "MM/dd/yyyy HH:mm";
    private final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final DateFormat altDateFormat = new SimpleDateFormat(ALT_DATE_FORMAT);

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
            CoronaVirusReportDataModel coronaVirusReportDataModel = new CoronaVirusReportDataModel();
            coronaVirusReportDataModel.setCountry(csvRecord.getField(CoronaReportCSVHeaders.COUNTRY_REGION.toString()));
            coronaVirusReportDataModel.setState(csvRecord.getField(CoronaReportCSVHeaders.STATE_PROVINCE.toString()));
            String latitude = csvRecord.getField(CoronaReportCSVHeaders.LATITUDE.toString());
            if(latitude != null && !latitude.isEmpty()) coronaVirusReportDataModel.setLatitude(new BigDecimal(latitude));
            String longitude = csvRecord.getField(CoronaReportCSVHeaders.LONGITUDE.toString());
            if(longitude != null && !longitude.isEmpty()) coronaVirusReportDataModel.setLongitude(new BigDecimal(longitude));
            coronaVirusReportDataModel.setReportedDate(buildDateTime(csvRecord.getField(CoronaReportCSVHeaders.LAST_UPDATE.toString())));
            String confirmed = csvRecord.getField(CoronaReportCSVHeaders.CONFIRMED.toString());
            coronaVirusReportDataModel.setConfirmed(!confirmed.isEmpty() ? Long.valueOf(confirmed) : null);
            String deaths = csvRecord.getField(CoronaReportCSVHeaders.DEATHS.toString());
            coronaVirusReportDataModel.setDeaths(!deaths.isEmpty() ? Integer.valueOf(deaths) : null);
            String recovered = csvRecord.getField(CoronaReportCSVHeaders.RECOVERED.toString());
            coronaVirusReportDataModel.setRecovered(!recovered.isEmpty() ? Long.valueOf(recovered) : null);

            coronaVirusReportDataModels.add(coronaVirusReportDataModel);
        }
        return coronaVirusReportDataModels;
    }

    private Timestamp buildDateTime(String date) {
        Timestamp dateTime = null;
        if(date != null) {
            try {
                if(!date.contains("/")) {
                    dateTime = new Timestamp(dateFormat.parse(date).getTime());
                }else {
                    dateTime = new Timestamp(altDateFormat.parse(date).getTime());
                }
            } catch (ParseException e) {
                log.error("Unable to parse date = {}", date);
            }
        }
        return dateTime;
    }

}
