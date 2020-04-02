/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights;


import com.corona.insights.jooq.corona_insights.tables.AgeWiseCases;
import com.corona.insights.jooq.corona_insights.tables.Cases;
import com.corona.insights.jooq.corona_insights.tables.CountryWise;
import com.corona.insights.jooq.corona_insights.tables.Location;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>corona_insights</code> 
 * schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index AGE_WISE_CASES_PRIMARY = Indexes0.AGE_WISE_CASES_PRIMARY;
    public static final Index CASES_COMPOSITE_UNIQUE_INDEX = Indexes0.CASES_COMPOSITE_UNIQUE_INDEX;
    public static final Index CASES_FILE_NAME = Indexes0.CASES_FILE_NAME;
    public static final Index CASES_PRIMARY = Indexes0.CASES_PRIMARY;
    public static final Index COUNTRY_WISE_PRIMARY = Indexes0.COUNTRY_WISE_PRIMARY;
    public static final Index LOCATION_PRIMARY = Indexes0.LOCATION_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index AGE_WISE_CASES_PRIMARY = Internal.createIndex("PRIMARY", AgeWiseCases.AGE_WISE_CASES, new OrderField[] { AgeWiseCases.AGE_WISE_CASES.ID }, true);
        public static Index CASES_COMPOSITE_UNIQUE_INDEX = Internal.createIndex("composite_unique_index", Cases.CASES, new OrderField[] { Cases.CASES.LOCATION_ID, Cases.CASES.REPORTING_TIMESTAMP, Cases.CASES.RECOVERED, Cases.CASES.CONFIRMED, Cases.CASES.DEATHS, Cases.CASES.FILE_NAME }, false);
        public static Index CASES_FILE_NAME = Internal.createIndex("file_name", Cases.CASES, new OrderField[] { Cases.CASES.FILE_NAME }, false);
        public static Index CASES_PRIMARY = Internal.createIndex("PRIMARY", Cases.CASES, new OrderField[] { Cases.CASES.ID }, true);
        public static Index COUNTRY_WISE_PRIMARY = Internal.createIndex("PRIMARY", CountryWise.COUNTRY_WISE, new OrderField[] { CountryWise.COUNTRY_WISE.ID }, true);
        public static Index LOCATION_PRIMARY = Internal.createIndex("PRIMARY", Location.LOCATION, new OrderField[] { Location.LOCATION.ID }, true);
    }
}
