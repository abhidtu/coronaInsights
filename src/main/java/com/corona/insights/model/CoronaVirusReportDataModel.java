package com.corona.insights.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CoronaVirusReportDataModel {

    private String country;
    private String state;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDate reportedDate;
    private Long confirmed;
    private Integer deaths;
    private Long recovered;
    private Timestamp reportedTimestamp;

}