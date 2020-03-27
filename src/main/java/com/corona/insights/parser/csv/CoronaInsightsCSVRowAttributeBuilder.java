package com.corona.insights.parser.csv;

import de.siegmar.fastcsv.reader.CsvRow;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Slf4j
public class CoronaInsightsCSVRowAttributeBuilder extends CoronaInsightsAttributeBuilder {

    private CsvRow csvRow;

    public CoronaInsightsCSVRowAttributeBuilder(CsvRow csvRow) {
        this.csvRow = csvRow;
    }

    @Override
    public String getCountry() {
        String countryRegion = csvRow.getField(CoronaReportCSVHeaders.COUNTRY_REGION.toString());
        String altCountryRegion = csvRow.getField(CoronaReportCSVHeaders.ALT_COUNTRY_REGION.toString());
        return countryRegion == null ? altCountryRegion : countryRegion;
    }

    @Override
    public String getState() {
        String stateProvince = csvRow.getField(CoronaReportCSVHeaders.STATE_PROVINCE.toString());
        String altStateProvince = csvRow.getField(CoronaReportCSVHeaders.ALT_STATE_PROVINCE.toString());
        return stateProvince == null ? altStateProvince : altStateProvince;
    }

    @Override
    public BigDecimal getLatitude() {
        String latitude = csvRow.getField(CoronaReportCSVHeaders.LATITUDE.toString());
        String altLatitude = csvRow.getField(CoronaReportCSVHeaders.ALT_LATITUDE.toString());
        String resLatitude = latitude == null ? altLatitude : latitude;
        return (resLatitude != null && !resLatitude.isEmpty()) ? new BigDecimal(resLatitude) : null;
    }

    @Override
    public BigDecimal getLongitude() {
        String longitude = csvRow.getField(CoronaReportCSVHeaders.LONGITUDE.toString());
        String altLongitude = csvRow.getField(CoronaReportCSVHeaders.ALT_LONGITUDE.toString());
        String resLongitude = longitude == null ? altLongitude : longitude;
        return (resLongitude != null && !resLongitude.isEmpty()) ? new BigDecimal(resLongitude) : null;
    }

    @Override
    public Timestamp getLastUpdateTimestamp() {
        String lastUpdated = csvRow.getField(CoronaReportCSVHeaders.LAST_UPDATE.toString());
        String altLastUpdated = csvRow.getField(CoronaReportCSVHeaders.ALT_LAST_UPDATE.toString());
        return buildDateTime(lastUpdated == null ? altLastUpdated : lastUpdated);
    }

    @Override
    public LocalDate getLastUpdateDate() {
        Timestamp lastUpdatedTimestamp = getLastUpdateTimestamp();
        LocalDate lastUpdateDate = null;
        if(lastUpdatedTimestamp != null) {
            lastUpdateDate = getLastUpdateTimestamp().toLocalDateTime().toLocalDate();
        }
        return lastUpdateDate;
    }

    @Override
    public Long getConfirmed() {
        String confirmed = csvRow.getField(CoronaReportCSVHeaders.CONFIRMED.toString());
        String altConfirmed = csvRow.getField(CoronaReportCSVHeaders.ALT_CONFIRMED.toString());
        String resConfirmed = confirmed == null ? altConfirmed : confirmed;
        if (resConfirmed == null) return null;
        return !resConfirmed.isEmpty() ? Long.valueOf(resConfirmed) : null;
    }

    @Override
    public Integer getDeaths() {
        String deaths = csvRow.getField(CoronaReportCSVHeaders.DEATHS.toString());
        String altDeaths = csvRow.getField(CoronaReportCSVHeaders.ALT_DEATHS.toString());
        String resDeaths =  deaths == null ? altDeaths : deaths;
        if (resDeaths == null) return null;
        return !resDeaths.isEmpty() ? Integer.valueOf(resDeaths) : null;
    }

    @Override
    public Long getRecovered() {
        String recovered = csvRow.getField(CoronaReportCSVHeaders.RECOVERED.toString());
        String altRecovered = csvRow.getField(CoronaReportCSVHeaders.ALT_RECOVERED.toString());
        String resRecovered = recovered == null ? altRecovered : recovered;
        if (resRecovered == null) return null;
        return !resRecovered.isEmpty() ? Long.valueOf(resRecovered) : null;
    }

}
