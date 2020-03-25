package com.corona.insights.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
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