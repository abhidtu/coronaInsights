package com.corona.insights.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Slf4j
@Getter
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
    Date reportedDate;

}