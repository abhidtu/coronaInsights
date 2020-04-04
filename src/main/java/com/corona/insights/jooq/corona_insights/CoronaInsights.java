/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights;


import com.corona.insights.jooq.DefaultCatalog;
import com.corona.insights.jooq.corona_insights.tables.AgeWiseCases;
import com.corona.insights.jooq.corona_insights.tables.Cases;
import com.corona.insights.jooq.corona_insights.tables.CountryWise;
import com.corona.insights.jooq.corona_insights.tables.Location;
import com.corona.insights.jooq.corona_insights.tables.Properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoronaInsights extends SchemaImpl {

    private static final long serialVersionUID = -583234447;

    /**
     * The reference instance of <code>corona_insights</code>
     */
    public static final CoronaInsights CORONA_INSIGHTS = new CoronaInsights();

    /**
     * The table <code>corona_insights.age_wise_cases</code>.
     */
    public final AgeWiseCases AGE_WISE_CASES = com.corona.insights.jooq.corona_insights.tables.AgeWiseCases.AGE_WISE_CASES;

    /**
     * The table <code>corona_insights.cases</code>.
     */
    public final Cases CASES = com.corona.insights.jooq.corona_insights.tables.Cases.CASES;

    /**
     * The table <code>corona_insights.country_wise</code>.
     */
    public final CountryWise COUNTRY_WISE = com.corona.insights.jooq.corona_insights.tables.CountryWise.COUNTRY_WISE;

    /**
     * The table <code>corona_insights.location</code>.
     */
    public final Location LOCATION = com.corona.insights.jooq.corona_insights.tables.Location.LOCATION;

    /**
     * The table <code>corona_insights.properties</code>.
     */
    public final Properties PROPERTIES = com.corona.insights.jooq.corona_insights.tables.Properties.PROPERTIES;

    /**
     * No further instances allowed
     */
    private CoronaInsights() {
        super("corona_insights", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            AgeWiseCases.AGE_WISE_CASES,
            Cases.CASES,
            CountryWise.COUNTRY_WISE,
            Location.LOCATION,
            Properties.PROPERTIES);
    }
}
