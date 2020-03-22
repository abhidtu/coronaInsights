package com.corona.insights.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class CoronaVirusReportDataModel {

    private String country;
    private String state;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp reportedDate;
    private Long confirmed;
    private Integer deaths;
    private Long recovered;

}