package com.corona.insights.model;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Date;

@Slf4j
@Setter
public class CoronaVirusETLMetricsDTO {

    String country;
    String state;
    String district;
    BigDecimal latitude;
    BigDecimal longitude;
    Long confirmed;
    Integer deaths;
    Long recovered;
    Integer active;
    Integer deltaConfirmed;
    Integer deltaDeaths;
    Integer deltaActive;
    Integer deltaRecovered;
    Date reportedDate;

    public CoronaVirusETLMetricsDTO() {
        this.confirmed = 0L;
        this.deaths = 0;
        this.active = 0;
        this.recovered = 0L;
    }

    public void computeDelta(CoronaVirusETLMetricsDTO coronaVirusETLMetricsDTO) {
        deltaConfirmed = (int)(confirmed != null ? confirmed - coronaVirusETLMetricsDTO.getConfirmed() : 0 );
        deltaDeaths = (deaths != null ? deaths - coronaVirusETLMetricsDTO.getDeaths() : 0 - coronaVirusETLMetricsDTO.getDeaths());
        deltaActive = (active != null ? active - coronaVirusETLMetricsDTO.getActive() : 0 - coronaVirusETLMetricsDTO.getActive());
        deltaRecovered = (int)(recovered != null ? recovered - coronaVirusETLMetricsDTO.getRecovered() : 0 - coronaVirusETLMetricsDTO.getRecovered());
    }

    public Long getConfirmed() {
        return confirmed == null ? 0L : confirmed;
    }

    public Integer getDeaths() {
        return deaths == null ? 0 : deaths;
    }

    public Long getRecovered() {
        return recovered == null ? 0L : recovered;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public Integer getActive() {
        return active;
    }

    public Integer getDeltaConfirmed() {
        return deltaConfirmed < 0 ? 0 : deltaConfirmed;
    }

    public Integer getDeltaDeaths() {
        return deltaDeaths < 0 ? 0 : deltaDeaths;
    }

    public Integer getDeltaActive() {
        return deltaActive < 0 ? 0 : deltaActive;
    }

    public Integer getDeltaRecovered() {
        return deltaRecovered < 0 ? 0 : deltaRecovered;
    }
}