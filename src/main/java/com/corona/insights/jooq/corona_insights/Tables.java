/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights;


import com.corona.insights.jooq.corona_insights.tables.AgeWiseCases;
import com.corona.insights.jooq.corona_insights.tables.Cases;
import com.corona.insights.jooq.corona_insights.tables.Location;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in corona_insights
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>corona_insights.age_wise_cases</code>.
     */
    public static final AgeWiseCases AGE_WISE_CASES = com.corona.insights.jooq.corona_insights.tables.AgeWiseCases.AGE_WISE_CASES;

    /**
     * The table <code>corona_insights.cases</code>.
     */
    public static final Cases CASES = com.corona.insights.jooq.corona_insights.tables.Cases.CASES;

    /**
     * The table <code>corona_insights.location</code>.
     */
    public static final Location LOCATION = com.corona.insights.jooq.corona_insights.tables.Location.LOCATION;
}