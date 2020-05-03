package com.corona.insights.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReportingDateLocationIds {

    private Date reportingDate;
    private List<Integer> locationIds;

}
