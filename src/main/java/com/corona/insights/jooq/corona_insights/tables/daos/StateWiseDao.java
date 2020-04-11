/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.daos;


import com.corona.insights.jooq.corona_insights.tables.StateWise;
import com.corona.insights.jooq.corona_insights.tables.records.StateWiseRecord;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


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
public class StateWiseDao extends DAOImpl<StateWiseRecord, com.corona.insights.jooq.corona_insights.tables.pojos.StateWise, UInteger> {

    /**
     * Create a new StateWiseDao without any configuration
     */
    public StateWiseDao() {
        super(StateWise.STATE_WISE, com.corona.insights.jooq.corona_insights.tables.pojos.StateWise.class);
    }

    /**
     * Create a new StateWiseDao with an attached configuration
     */
    public StateWiseDao(Configuration configuration) {
        super(StateWise.STATE_WISE, com.corona.insights.jooq.corona_insights.tables.pojos.StateWise.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.corona.insights.jooq.corona_insights.tables.pojos.StateWise object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchById(UInteger... values) {
        return fetch(StateWise.STATE_WISE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.corona.insights.jooq.corona_insights.tables.pojos.StateWise fetchOneById(UInteger value) {
        return fetchOne(StateWise.STATE_WISE.ID, value);
    }

    /**
     * Fetch records that have <code>reporting_date IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByReportingDate(Date... values) {
        return fetch(StateWise.STATE_WISE.REPORTING_DATE, values);
    }

    /**
     * Fetch records that have <code>country IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByCountry(String... values) {
        return fetch(StateWise.STATE_WISE.COUNTRY, values);
    }

    /**
     * Fetch records that have <code>state IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByState(String... values) {
        return fetch(StateWise.STATE_WISE.STATE, values);
    }

    /**
     * Fetch records that have <code>confirmed IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByConfirmed(Long... values) {
        return fetch(StateWise.STATE_WISE.CONFIRMED, values);
    }

    /**
     * Fetch records that have <code>deaths IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByDeaths(Integer... values) {
        return fetch(StateWise.STATE_WISE.DEATHS, values);
    }

    /**
     * Fetch records that have <code>recovered IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByRecovered(Long... values) {
        return fetch(StateWise.STATE_WISE.RECOVERED, values);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByCreatedAt(Timestamp... values) {
        return fetch(StateWise.STATE_WISE.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>modified_at IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateWise> fetchByModifiedAt(Timestamp... values) {
        return fetch(StateWise.STATE_WISE.MODIFIED_AT, values);
    }
}