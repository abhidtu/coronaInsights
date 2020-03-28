/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights.tables;


import com.corona.insights.jooq.corona_insights.CoronaInsights;
import com.corona.insights.jooq.corona_insights.Indexes;
import com.corona.insights.jooq.corona_insights.Keys;
import com.corona.insights.jooq.corona_insights.tables.records.AgeWiseCasesRecord;

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
import org.jooq.types.ULong;


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
public class AgeWiseCases extends TableImpl<AgeWiseCasesRecord> {

    private static final long serialVersionUID = -1761214898;

    /**
     * The reference instance of <code>corona_insights.age_wise_cases</code>
     */
    public static final AgeWiseCases AGE_WISE_CASES = new AgeWiseCases();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AgeWiseCasesRecord> getRecordType() {
        return AgeWiseCasesRecord.class;
    }

    /**
     * The column <code>corona_insights.age_wise_cases.id</code>.
     */
    public final TableField<AgeWiseCasesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.location_id</code>.
     */
    public final TableField<AgeWiseCasesRecord, Integer> LOCATION_ID = createField("location_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.age</code>.
     */
    public final TableField<AgeWiseCasesRecord, Integer> AGE = createField("age", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.reporting_date</code>.
     */
    public final TableField<AgeWiseCasesRecord, Timestamp> REPORTING_DATE = createField("reporting_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.confirmed</code>.
     */
    public final TableField<AgeWiseCasesRecord, Long> CONFIRMED = createField("confirmed", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.deaths</code>.
     */
    public final TableField<AgeWiseCasesRecord, ULong> DEATHS = createField("deaths", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.recovered</code>.
     */
    public final TableField<AgeWiseCasesRecord, ULong> RECOVERED = createField("recovered", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.created_at</code>.
     */
    public final TableField<AgeWiseCasesRecord, Timestamp> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>corona_insights.age_wise_cases.modified_at</code>.
     */
    public final TableField<AgeWiseCasesRecord, Timestamp> MODIFIED_AT = createField("modified_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>corona_insights.age_wise_cases</code> table reference
     */
    public AgeWiseCases() {
        this(DSL.name("age_wise_cases"), null);
    }

    /**
     * Create an aliased <code>corona_insights.age_wise_cases</code> table reference
     */
    public AgeWiseCases(String alias) {
        this(DSL.name(alias), AGE_WISE_CASES);
    }

    /**
     * Create an aliased <code>corona_insights.age_wise_cases</code> table reference
     */
    public AgeWiseCases(Name alias) {
        this(alias, AGE_WISE_CASES);
    }

    private AgeWiseCases(Name alias, Table<AgeWiseCasesRecord> aliased) {
        this(alias, aliased, null);
    }

    private AgeWiseCases(Name alias, Table<AgeWiseCasesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AgeWiseCases(Table<O> child, ForeignKey<O, AgeWiseCasesRecord> key) {
        super(child, key, AGE_WISE_CASES);
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
        return Arrays.<Index>asList(Indexes.AGE_WISE_CASES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AgeWiseCasesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_AGE_WISE_CASES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AgeWiseCasesRecord> getPrimaryKey() {
        return Keys.KEY_AGE_WISE_CASES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AgeWiseCasesRecord>> getKeys() {
        return Arrays.<UniqueKey<AgeWiseCasesRecord>>asList(Keys.KEY_AGE_WISE_CASES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgeWiseCases as(String alias) {
        return new AgeWiseCases(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgeWiseCases as(Name alias) {
        return new AgeWiseCases(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AgeWiseCases rename(String name) {
        return new AgeWiseCases(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AgeWiseCases rename(Name name) {
        return new AgeWiseCases(name, null);
    }
}
