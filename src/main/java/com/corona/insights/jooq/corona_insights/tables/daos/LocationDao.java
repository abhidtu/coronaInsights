/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.daos;


import com.corona.insights.jooq.corona_insights.tables.Location;
import com.corona.insights.jooq.corona_insights.tables.records.LocationRecord;

import java.math.BigDecimal;
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
public class LocationDao extends DAOImpl<LocationRecord, com.corona.insights.jooq.corona_insights.tables.pojos.Location, UInteger> {

    /**
     * Create a new LocationDao without any configuration
     */
    public LocationDao() {
        super(Location.LOCATION, com.corona.insights.jooq.corona_insights.tables.pojos.Location.class);
    }

    /**
     * Create a new LocationDao with an attached configuration
     */
    public LocationDao(Configuration configuration) {
        super(Location.LOCATION, com.corona.insights.jooq.corona_insights.tables.pojos.Location.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.corona.insights.jooq.corona_insights.tables.pojos.Location object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchById(UInteger... values) {
        return fetch(Location.LOCATION.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.corona.insights.jooq.corona_insights.tables.pojos.Location fetchOneById(UInteger value) {
        return fetchOne(Location.LOCATION.ID, value);
    }

    /**
     * Fetch records that have <code>country IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchByCountry(String... values) {
        return fetch(Location.LOCATION.COUNTRY, values);
    }

    /**
     * Fetch records that have <code>state IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchByState(String... values) {
        return fetch(Location.LOCATION.STATE, values);
    }

    /**
     * Fetch records that have <code>latitude IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchByLatitude(BigDecimal... values) {
        return fetch(Location.LOCATION.LATITUDE, values);
    }

    /**
     * Fetch records that have <code>longitude IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchByLongitude(BigDecimal... values) {
        return fetch(Location.LOCATION.LONGITUDE, values);
    }

    /**
     * Fetch records that have <code>file_name IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchByFileName(String... values) {
        return fetch(Location.LOCATION.FILE_NAME, values);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchByCreatedAt(Timestamp... values) {
        return fetch(Location.LOCATION.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>modified_at IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.Location> fetchByModifiedAt(Timestamp... values) {
        return fetch(Location.LOCATION.MODIFIED_AT, values);
    }
}