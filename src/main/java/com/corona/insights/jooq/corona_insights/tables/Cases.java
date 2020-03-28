/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables;


import com.corona.insights.jooq.corona_insights.CoronaInsights;
import com.corona.insights.jooq.corona_insights.Indexes;
import com.corona.insights.jooq.corona_insights.Keys;
import com.corona.insights.jooq.corona_insights.tables.records.CasesRecord;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
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
public class Cases extends TableImpl<CasesRecord> {

    private static final long serialVersionUID = -1208992384;

    /**
     * The reference instance of <code>corona_insights.cases</code>
     */
    public static final Cases CASES = new Cases();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CasesRecord> getRecordType() {
        return CasesRecord.class;
    }

    /**
     * The column <code>corona_insights.cases.id</code>.
     */
    public final TableField<CasesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>corona_insights.cases.location_id</code>.
     */
    public final TableField<CasesRecord, Integer> LOCATION_ID = createField("location_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>corona_insights.cases.reporting_date</code>.
     */
    public final TableField<CasesRecord, Date> REPORTING_DATE = createField("reporting_date", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>corona_insights.cases.confirmed</code>.
     */
    public final TableField<CasesRecord, Long> CONFIRMED = createField("confirmed", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>corona_insights.cases.deaths</code>.
     */
    public final TableField<CasesRecord, Integer> DEATHS = createField("deaths", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>corona_insights.cases.recovered</code>.
     */
    public final TableField<CasesRecord, Long> RECOVERED = createField("recovered", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>corona_insights.cases.reporting_timestamp</code>.
     */
    public final TableField<CasesRecord, Timestamp> REPORTING_TIMESTAMP = createField("reporting_timestamp", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>corona_insights.cases.file_name</code>.
     */
    public final TableField<CasesRecord, String> FILE_NAME = createField("file_name", org.jooq.impl.SQLDataType.VARCHAR(250).nullable(false), this, "");

    /**
     * The column <code>corona_insights.cases.created_At</code>.
     */
    public final TableField<CasesRecord, Timestamp> CREATED_AT = createField("created_At", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>corona_insights.cases.modified_at</code>.
     */
    public final TableField<CasesRecord, Timestamp> MODIFIED_AT = createField("modified_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>corona_insights.cases</code> table reference
     */
    public Cases() {
        this(DSL.name("cases"), null);
    }

    /**
     * Create an aliased <code>corona_insights.cases</code> table reference
     */
    public Cases(String alias) {
        this(DSL.name(alias), CASES);
    }

    /**
     * Create an aliased <code>corona_insights.cases</code> table reference
     */
    public Cases(Name alias) {
        this(alias, CASES);
    }

    private Cases(Name alias, Table<CasesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Cases(Name alias, Table<CasesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Cases(Table<O> child, ForeignKey<O, CasesRecord> key) {
        super(child, key, CASES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return CoronaInsights.CORONA_INSIGHTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CASES_FILE_NAME, Indexes.CASES_PRIMARY, Indexes.CASES_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CasesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_CASES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CasesRecord> getPrimaryKey() {
        return Keys.KEY_CASES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CasesRecord>> getKeys() {
        return Arrays.<UniqueKey<CasesRecord>>asList(Keys.KEY_CASES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cases as(String alias) {
        return new Cases(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cases as(Name alias) {
        return new Cases(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Cases rename(String name) {
        return new Cases(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Cases rename(Name name) {
        return new Cases(name, null);
    }
}