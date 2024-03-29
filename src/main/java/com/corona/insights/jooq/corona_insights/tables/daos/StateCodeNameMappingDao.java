/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.daos;


import com.corona.insights.jooq.corona_insights.tables.StateCodeNameMapping;
import com.corona.insights.jooq.corona_insights.tables.records.StateCodeNameMappingRecord;

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
public class StateCodeNameMappingDao extends DAOImpl<StateCodeNameMappingRecord, com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping, UInteger> {

    /**
     * Create a new StateCodeNameMappingDao without any configuration
     */
    public StateCodeNameMappingDao() {
        super(StateCodeNameMapping.STATE_CODE_NAME_MAPPING, com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping.class);
    }

    /**
     * Create a new StateCodeNameMappingDao with an attached configuration
     */
    public StateCodeNameMappingDao(Configuration configuration) {
        super(StateCodeNameMapping.STATE_CODE_NAME_MAPPING, com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping> fetchById(UInteger... values) {
        return fetch(StateCodeNameMapping.STATE_CODE_NAME_MAPPING.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping fetchOneById(UInteger value) {
        return fetchOne(StateCodeNameMapping.STATE_CODE_NAME_MAPPING.ID, value);
    }

    /**
     * Fetch records that have <code>state IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping> fetchByState(String... values) {
        return fetch(StateCodeNameMapping.STATE_CODE_NAME_MAPPING.STATE, values);
    }

    /**
     * Fetch records that have <code>Abbrev IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping> fetchByAbbrev(String... values) {
        return fetch(StateCodeNameMapping.STATE_CODE_NAME_MAPPING.ABBREV, values);
    }

    /**
     * Fetch records that have <code>Code IN (values)</code>
     */
    public List<com.corona.insights.jooq.corona_insights.tables.pojos.StateCodeNameMapping> fetchByCode(String... values) {
        return fetch(StateCodeNameMapping.STATE_CODE_NAME_MAPPING.CODE, values);
    }
}
