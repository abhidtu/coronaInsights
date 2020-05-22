/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ZipCodeMapping implements Serializable {

    private static final long serialVersionUID = -96166549;

    private Integer    zip;
    private String     city;
    private String     state;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer    timezone;
    private Boolean    daylightSavingsTimeFlag;
    private String     geopoint;
    private String     stateName;

    public ZipCodeMapping() {}

    public ZipCodeMapping(ZipCodeMapping value) {
        this.zip = value.zip;
        this.city = value.city;
        this.state = value.state;
        this.latitude = value.latitude;
        this.longitude = value.longitude;
        this.timezone = value.timezone;
        this.daylightSavingsTimeFlag = value.daylightSavingsTimeFlag;
        this.geopoint = value.geopoint;
        this.stateName = value.stateName;
    }

    public ZipCodeMapping(
        Integer    zip,
        String     city,
        String     state,
        BigDecimal latitude,
        BigDecimal longitude,
        Integer    timezone,
        Boolean    daylightSavingsTimeFlag,
        String     geopoint,
        String     stateName
    ) {
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.daylightSavingsTimeFlag = daylightSavingsTimeFlag;
        this.geopoint = geopoint;
        this.stateName = stateName;
    }

    public Integer getZip() {
        return this.zip;
    }

    public ZipCodeMapping setZip(Integer zip) {
        this.zip = zip;
        return this;
    }

    public String getCity() {
        return this.city;
    }

    public ZipCodeMapping setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return this.state;
    }

    public ZipCodeMapping setState(String state) {
        this.state = state;
        return this;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public ZipCodeMapping setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public ZipCodeMapping setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public Integer getTimezone() {
        return this.timezone;
    }

    public ZipCodeMapping setTimezone(Integer timezone) {
        this.timezone = timezone;
        return this;
    }

    public Boolean getDaylightSavingsTimeFlag() {
        return this.daylightSavingsTimeFlag;
    }

    public ZipCodeMapping setDaylightSavingsTimeFlag(Boolean daylightSavingsTimeFlag) {
        this.daylightSavingsTimeFlag = daylightSavingsTimeFlag;
        return this;
    }

    public String getGeopoint() {
        return this.geopoint;
    }

    public ZipCodeMapping setGeopoint(String geopoint) {
        this.geopoint = geopoint;
        return this;
    }

    public String getStateName() {
        return this.stateName;
    }

    public ZipCodeMapping setStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ZipCodeMapping (");

        sb.append(zip);
        sb.append(", ").append(city);
        sb.append(", ").append(state);
        sb.append(", ").append(latitude);
        sb.append(", ").append(longitude);
        sb.append(", ").append(timezone);
        sb.append(", ").append(daylightSavingsTimeFlag);
        sb.append(", ").append(geopoint);
        sb.append(", ").append(stateName);

        sb.append(")");
        return sb.toString();
    }
}
