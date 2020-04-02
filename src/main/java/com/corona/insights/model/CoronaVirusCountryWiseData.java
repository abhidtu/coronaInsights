package com.corona.insights.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Slf4j
@Getter
@Setter
public class CoronaVirusCountryWiseData {

    Long confirmed;
    Integer deaths;
    Long recovered;
    Date reportedDate;

}