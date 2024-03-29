/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables.records;


import com.corona.insights.jooq.corona_insights.enums.CountryWiseSource;
import com.corona.insights.jooq.corona_insights.tables.CountryWise;

import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record14;
import org.jooq.Row14;
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
public class CountryWiseRecord extends UpdatableRecordImpl<CountryWiseRecord> implements Record14<Integer, Date, String, Integer, Integer, Integer, Long, Integer, Long, Long, Integer, CountryWiseSource, Timestamp, Timestamp> {

    private static final long serialVersionUID = 182165400;

    /**
     * Setter for <code>corona_insights.country_wise.id</code>.
     */
    public CountryWiseRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>corona_insights.country_wise.reporting_date</code>.
     */
    public CountryWiseRecord setReportingDate(Date value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.reporting_date</code>.
     */
    public Date getReportingDate() {
        return (Date) get(1);
    }

    /**
     * Setter for <code>corona_insights.country_wise.country</code>.
     */
    public CountryWiseRecord setCountry(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.country</code>.
     */
    public String getCountry() {
        return (String) get(2);
    }

    /**
     * Setter for <code>corona_insights.country_wise.delta_confirmed</code>.
     */
    public CountryWiseRecord setDeltaConfirmed(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.delta_confirmed</code>.
     */
    public Integer getDeltaConfirmed() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>corona_insights.country_wise.delta_deaths</code>.
     */
    public CountryWiseRecord setDeltaDeaths(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.delta_deaths</code>.
     */
    public Integer getDeltaDeaths() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>corona_insights.country_wise.delta_recovered</code>.
     */
    public CountryWiseRecord setDeltaRecovered(Integer value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.delta_recovered</code>.
     */
    public Integer getDeltaRecovered() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>corona_insights.country_wise.confirmed</code>.
     */
    public CountryWiseRecord setConfirmed(Long value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.confirmed</code>.
     */
    public Long getConfirmed() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>corona_insights.country_wise.deaths</code>.
     */
    public CountryWiseRecord setDeaths(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.deaths</code>.
     */
    public Integer getDeaths() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>corona_insights.country_wise.recovered</code>.
     */
    public CountryWiseRecord setRecovered(Long value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.recovered</code>.
     */
    public Long getRecovered() {
        return (Long) get(8);
    }

    /**
     * Setter for <code>corona_insights.country_wise.active</code>.
     */
    public CountryWiseRecord setActive(Long value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.active</code>.
     */
    public Long getActive() {
        return (Long) get(9);
    }

    /**
     * Setter for <code>corona_insights.country_wise.delta_active</code>.
     */
    public CountryWiseRecord setDeltaActive(Integer value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.delta_active</code>.
     */
    public Integer getDeltaActive() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>corona_insights.country_wise.source</code>.
     */
    public CountryWiseRecord setSource(CountryWiseSource value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.source</code>.
     */
    public CountryWiseSource getSource() {
        return (CountryWiseSource) get(11);
    }

    /**
     * Setter for <code>corona_insights.country_wise.created_at</code>.
     */
    public CountryWiseRecord setCreatedAt(Timestamp value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.created_at</code>.
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(12);
    }

    /**
     * Setter for <code>corona_insights.country_wise.modified_at</code>.
     */
    public CountryWiseRecord setModifiedAt(Timestamp value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>corona_insights.country_wise.modified_at</code>.
     */
    public Timestamp getModifiedAt() {
        return (Timestamp) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Integer, Date, String, Integer, Integer, Integer, Long, Integer, Long, Long, Integer, CountryWiseSource, Timestamp, Timestamp> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row14<Integer, Date, String, Integer, Integer, Integer, Long, Integer, Long, Long, Integer, CountryWiseSource, Timestamp, Timestamp> valuesRow() {
        return (Row14) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return CountryWise.COUNTRY_WISE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field2() {
        return CountryWise.COUNTRY_WISE.REPORTING_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return CountryWise.COUNTRY_WISE.COUNTRY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return CountryWise.COUNTRY_WISE.DELTA_CONFIRMED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return CountryWise.COUNTRY_WISE.DELTA_DEATHS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return CountryWise.COUNTRY_WISE.DELTA_RECOVERED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field7() {
        return CountryWise.COUNTRY_WISE.CONFIRMED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return CountryWise.COUNTRY_WISE.DEATHS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field9() {
        return CountryWise.COUNTRY_WISE.RECOVERED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field10() {
        return CountryWise.COUNTRY_WISE.ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field11() {
        return CountryWise.COUNTRY_WISE.DELTA_ACTIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<CountryWiseSource> field12() {
        return CountryWise.COUNTRY_WISE.SOURCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field13() {
        return CountryWise.COUNTRY_WISE.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field14() {
        return CountryWise.COUNTRY_WISE.MODIFIED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date component2() {
        return getReportingDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getCountry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getDeltaConfirmed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getDeltaDeaths();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getDeltaRecovered();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component7() {
        return getConfirmed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component8() {
        return getDeaths();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component9() {
        return getRecovered();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component10() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component11() {
        return getDeltaActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseSource component12() {
        return getSource();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component13() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component14() {
        return getModifiedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value2() {
        return getReportingDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getCountry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getDeltaConfirmed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getDeltaDeaths();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getDeltaRecovered();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value7() {
        return getConfirmed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getDeaths();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value9() {
        return getRecovered();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value10() {
        return getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value11() {
        return getDeltaActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseSource value12() {
        return getSource();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value13() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value14() {
        return getModifiedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value2(Date value) {
        setReportingDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value3(String value) {
        setCountry(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value4(Integer value) {
        setDeltaConfirmed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value5(Integer value) {
        setDeltaDeaths(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value6(Integer value) {
        setDeltaRecovered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value7(Long value) {
        setConfirmed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value8(Integer value) {
        setDeaths(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value9(Long value) {
        setRecovered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value10(Long value) {
        setActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value11(Integer value) {
        setDeltaActive(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value12(CountryWiseSource value) {
        setSource(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value13(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord value14(Timestamp value) {
        setModifiedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryWiseRecord values(Integer value1, Date value2, String value3, Integer value4, Integer value5, Integer value6, Long value7, Integer value8, Long value9, Long value10, Integer value11, CountryWiseSource value12, Timestamp value13, Timestamp value14) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CountryWiseRecord
     */
    public CountryWiseRecord() {
        super(CountryWise.COUNTRY_WISE);
    }

    /**
     * Create a detached, initialised CountryWiseRecord
     */
    public CountryWiseRecord(Integer id, Date reportingDate, String country, Integer deltaConfirmed, Integer deltaDeaths, Integer deltaRecovered, Long confirmed, Integer deaths, Long recovered, Long active, Integer deltaActive, CountryWiseSource source, Timestamp createdAt, Timestamp modifiedAt) {
        super(CountryWise.COUNTRY_WISE);

        set(0, id);
        set(1, reportingDate);
        set(2, country);
        set(3, deltaConfirmed);
        set(4, deltaDeaths);
        set(5, deltaRecovered);
        set(6, confirmed);
        set(7, deaths);
        set(8, recovered);
        set(9, active);
        set(10, deltaActive);
        set(11, source);
        set(12, createdAt);
        set(13, modifiedAt);
    }
}
