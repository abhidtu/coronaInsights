package com.corona.insights.parser.csv;

public enum CoronaReportCSVHeaders {

    STATE_PROVINCE("Province/State"),
    COUNTRY_REGION("Country/Region"),
    LAST_UPDATE("Last Update"),
    CONFIRMED("Confirmed"),
    DEATHS("Deaths"),
    RECOVERED("Recovered"),
    LATITUDE("Latitude"),
    LONGITUDE("Longitude"),

    ALT_STATE_PROVINCE("Province_State"),
    ALT_COUNTRY_REGION("Country_Region"),
    ALT_LAST_UPDATE("Last_Update"),
    ALT_CONFIRMED("Confirmed"),
    ALT_DEATHS("Deaths"),
    ALT_RECOVERED("Recovered"),
    ALT_LATITUDE("Lat"),
    ALT_LONGITUDE("Long_");

    private final String header;

    CoronaReportCSVHeaders(String header) {
        this.header = header;
    }

    @Override
    public String toString() { return header; }

}