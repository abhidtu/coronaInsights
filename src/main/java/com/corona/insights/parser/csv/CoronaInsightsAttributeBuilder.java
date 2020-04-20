package com.corona.insights.parser.csv;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Slf4j
public abstract class CoronaInsightsAttributeBuilder {

    private final String ALT_DATE_FORMAT = "MM/dd/yyyy HH:mm";
    private final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final DateFormat altDateFormat = new SimpleDateFormat(ALT_DATE_FORMAT);
    private final DateFormat dateFormat_2 = new SimpleDateFormat(DATE_FORMAT_2);

    public abstract String getCountry();

    public abstract String getState();

    public abstract String getDistrict();

    public abstract BigDecimal getLatitude();

    public abstract BigDecimal getLongitude();

    public abstract Timestamp getLastUpdateTimestamp();

    public abstract LocalDate getLastUpdateDate();

    public abstract Long getConfirmed();

    public abstract Integer getDeaths();

    public abstract Long getRecovered();

    protected Timestamp buildDateTime(String date) {
        Timestamp dateTime = null;
        if(date != null) {
            try {
                if(date.contains("T")) {
                    dateTime = new Timestamp(dateFormat.parse(date).getTime());
                }else if(date.contains("/")) {
                    dateTime = new Timestamp(altDateFormat.parse(checkAndFixDate(date)).getTime());
                }else {
                    dateTime = new Timestamp(dateFormat_2.parse(date).getTime());
                }
            } catch (ParseException e) {
                log.error("Unable to parse date = {}", date);
            }
        }
        return dateTime;
    }

    private String checkAndFixDate(String date) {
        String[] splittedDate = date.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splittedDate.length; i++) {
            if(i==2) {
                String[] yearTimeSplit  = splittedDate[i].split(" ");
                for (int j = 0; j < yearTimeSplit.length; j++) {
                    if(j==0) {
                        if(!yearTimeSplit[j].equals("2020")) {
                            sb.append(yearTimeSplit[j]).append("20 ");
                        }else {
                            sb.append(yearTimeSplit[j]).append(" ");
                        }
                    }else {
                        sb.append(yearTimeSplit[j]);
                    }
                }
            }else {
                sb.append(splittedDate[i]).append("/");
            }
        }
        return sb.toString();
    }

}
