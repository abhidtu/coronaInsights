package com.corona.insights.parser.csv;

public enum CoronaReportCSVHeaders {

    STATE_PROVINCE("Province/State"),
    COUNTRY_REGION("Country/Region"),
    LAST_UPDATE("Last Update"),
    CONFIRMED("Confirmed"),
    DEATHS("Deaths"),
    RECOVERED("Recovered"),
    LATITUDE("Latitude"),
    LONGITUDE("Longitude");

    private final String header;

    CoronaReportCSVHeaders(String header) {
        this.header = header;
    }

    @Override
    public String toString() { return header; }

}