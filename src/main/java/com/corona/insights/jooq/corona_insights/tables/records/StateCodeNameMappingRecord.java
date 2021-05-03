/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.records;


import com.corona.insights.jooq.corona_insights.tables.StateCodeNameMapping;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
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
public class StateCodeNameMappingRecord extends UpdatableRecordImpl<StateCodeNameMappingRecord> implements Record4<UInteger, String, String, String> {

    private static final long serialVersionUID = -2071059284;

    /**
     * Setter for <code>corona_insights.state_code_name_mapping.id</code>.
     */
    public StateCodeNameMappingRecord setId(UInteger value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.state_code_name_mapping.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>corona_insights.state_code_name_mapping.state</code>.
     */
    public StateCodeNameMappingRecord setState(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.state_code_name_mapping.state</code>.
     */
    public String getState() {
        return (String) get(1);
    }

    /**
     * Setter for <code>corona_insights.state_code_name_mapping.Abbrev</code>.
     */
    public StateCodeNameMappingRecord setAbbrev(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.state_code_name_mapping.Abbrev</code>.
     */
    public String getAbbrev() {
        return (String) get(2);
    }

    /**
     * Setter for <code>corona_insights.state_code_name_mapping.Code</code>.
     */
    public StateCodeNameMappingRecord setCode(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.state_code_name_mapping.Code</code>.
     */
    public String getCode() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return StateCodeNameMapping.STATE_CODE_NAME_MAPPING.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return StateCodeNameMapping.STATE_CODE_NAME_MAPPING.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return StateCodeNameMapping.STATE_CODE_NAME_MAPPING.ABBREV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return StateCodeNameMapping.STATE_CODE_NAME_MAPPING.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getAbbrev();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getAbbrev();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord value2(String value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord value3(String value) {
        setAbbrev(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord value4(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateCodeNameMappingRecord values(UInteger value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
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
    public StateCodeNameMappingRecord(UInteger id, String state, String abbrev, String code) {
        super(StateCodeNameMapping.STATE_CODE_NAME_MAPPING);

        set(0, id);
        set(1, state);
        set(2, abbrev);
        set(3, code);
    }
}