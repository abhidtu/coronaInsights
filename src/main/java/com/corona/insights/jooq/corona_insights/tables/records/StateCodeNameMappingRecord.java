/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.records;


import com.corona.insights.jooq.corona_insights.tables.StateCodeNameMapping;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


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
public class StateCodeNameMappingRecord extends UpdatableRecordImpl<StateCodeNameMappingRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = 620339747;

    /**
     * Setter for <code>corona_insights.state_code_name_mapping.State</code>.
     */
    public StateCodeNameMappingRecord setState(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.state_code_name_mapping.State</code>.
     */
    public String getState() {
        return (String) get(0);
    }

    /**
     * Setter for <code>corona_insights.state_code_name_mapping.Abbrev</code>.
     */
    public StateCodeNameMappingRecord setAbbrev(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.state_code_name_mapping.Abbrev</code>.
     */
    public String getAbbrev() {
        return (String) get(1);
    }

    /**
     * Setter for <code>corona_insights.state_code_name_mapping.Code</code>.
     */
    public StateCodeNameMappingRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.state_code_name_mapping.Code</code>.
     */
    public String getCode() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return StateCodeNameMapping.STATE_CODE_NAME_MAPPING.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return StateCodeNameMapping.STATE_CODE_NAME_MAPPING.ABBREV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return StateCodeNameMapping.STATE_CODE_NAME_MAPPING.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getAbbrev();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getAbbrev();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord value1(String value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord value2(String value) {
        setAbbrev(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StateCodeNameMappingRecord
     */
    public StateCodeNameMappingRecord() {
        super(StateCodeNameMapping.STATE_CODE_NAME_MAPPING);
    }

    /**
     * Create a detached, initialised StateCodeNameMappingRecord
     */
    public StateCodeNameMappingRecord(String state, String abbrev, String code) {
        super(StateCodeNameMapping.STATE_CODE_NAME_MAPPING);

        set(0, state);
        set(1, abbrev);
        set(2, code);
    }
}
