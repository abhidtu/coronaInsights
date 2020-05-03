/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.pojos;


import com.corona.insights.jooq.corona_insights.enums.DistrictWiseSource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.types.UInteger;


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
public class DistrictWise implements Serializable {

    private static final long serialVersionUID = 347166014;

    private UInteger           id;
    private Date               reportingDate;
    private String             country;
    private String             state;
    private String             district;
    private Long               confirmed;
    private Integer            deaths;
    private Long               recovered;
    private BigDecimal         latitude;
    private BigDecimal         longitude;
    private DistrictWiseSource source;
    private Timestamp          createdAt;
    private Timestamp          modifiedAt;

    public DistrictWise() {}

    public DistrictWise(DistrictWise value) {
        this.id = value.id;
        this.reportingDate = value.reportingDate;
        this.country = value.country;
        this.state = value.state;
        this.district = value.district;
        this.confirmed = value.confirmed;
        this.deaths = value.deaths;
        this.recovered = value.recovered;
        this.latitude = value.latitude;
        this.longitude = value.longitude;
        this.source = value.source;
        this.createdAt = value.createdAt;
        this.modifiedAt = value.modifiedAt;
    }

    public DistrictWise(
        UInteger           id,
        Date               reportingDate,
        String             country,
        String             state,
        String             district,
        Long               confirmed,
        Integer            deaths,
        Long               recovered,
        BigDecimal         latitude,
        BigDecimal         longitude,
        DistrictWiseSource source,
        Timestamp          createdAt,
        Timestamp          modifiedAt
    ) {
        this.id = id;
        this.reportingDate = reportingDate;
        this.country = country;
        this.state = state;
        this.district = district;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.latitude = latitude;
        this.longitude = longitude;
        this.source = source;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public UInteger getId() {
        return this.id;
    }

    public DistrictWise setId(UInteger id) {
        this.id = id;
        return this;
    }

    public Date getReportingDate() {
        return this.reportingDate;
    }

    public DistrictWise setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public DistrictWise setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return this.state;
    }

    public DistrictWise setState(String state) {
        this.state = state;
        return this;
    }

    public String getDistrict() {
        return this.district;
    }

    public DistrictWise setDistrict(String district) {
        this.district = district;
        return this;
    }

    public Long getConfirmed() {
        return this.confirmed;
    }

    public DistrictWise setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public Integer getDeaths() {
        return this.deaths;
    }

    public DistrictWise setDeaths(Integer deaths) {
        this.deaths = deaths;
        return this;
    }

    public Long getRecovered() {
        return this.recovered;
    }

    public DistrictWise setRecovered(Long recovered) {
        this.recovered = recovered;
        return this;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public DistrictWise setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public DistrictWise setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public DistrictWiseSource getSource() {
        return this.source;
    }

    public DistrictWise setSource(DistrictWiseSource source) {
        this.source = source;
        return this;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public DistrictWise setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getModifiedAt() {
        return this.modifiedAt;
    }

    public DistrictWise setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DistrictWise (");

        sb.append(id);
        sb.append(", ").append(reportingDate);
        sb.append(", ").append(country);
        sb.append(", ").append(state);
        sb.append(", ").append(district);
        sb.append(", ").append(confirmed);
        sb.append(", ").append(deaths);
        sb.append(", ").append(recovered);
        sb.append(", ").append(latitude);
        sb.append(", ").append(longitude);
        sb.append(", ").append(source);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(modifiedAt);

        sb.append(")");
        return sb.toString();
    }
}
