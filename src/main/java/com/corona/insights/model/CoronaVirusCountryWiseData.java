package com.corona.insights.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Slf4j
@Component
public class CoronaVirusCountryWiseData {

    String country;
    Long confirmed;
    Long deaths;
    Long recovered;
    String reportedDate;

}
