/*
 * This file is generated by jOOQ.
 */
package com.corona.insights.jooq.corona_insights;


import com.corona.insights.jooq.corona_insights.tables.AgeWiseCases;
import com.corona.insights.jooq.corona_insights.tables.Cases;
import com.corona.insights.jooq.corona_insights.tables.CountryWise;
import com.corona.insights.jooq.corona_insights.tables.Location;
import com.corona.insights.jooq.corona_insights.tables.records.AgeWiseCasesRecord;
import com.corona.insights.jooq.corona_insights.tables.records.CasesRecord;
import com.corona.insights.jooq.corona_insights.tables.records.CountryWiseRecord;
import com.corona.insights.jooq.corona_insights.tables.records.LocationRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;
import org.jooq.types.UInteger;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>corona_insights</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AgeWiseCasesRecord, UInteger> IDENTITY_AGE_WISE_CASES = Identities0.IDENTITY_AGE_WISE_CASES;
    public static final Identity<CasesRecord, Integer> IDENTITY_CASES = Identities0.IDENTITY_CASES;
    public static final Identity<CountryWiseRecord, Integer> IDENTITY_COUNTRY_WISE = Identities0.IDENTITY_COUNTRY_WISE;
    public static final Identity<LocationRecord, Integer> IDENTITY_LOCATION = Identities0.IDENTITY_LOCATION;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AgeWiseCasesRecord> KEY_AGE_WISE_CASES_PRIMARY = UniqueKeys0.KEY_AGE_WISE_CASES_PRIMARY;
    public static final UniqueKey<CasesRecord> KEY_CASES_PRIMARY = UniqueKeys0.KEY_CASES_PRIMARY;
    public static final UniqueKey<CountryWiseRecord> KEY_COUNTRY_WISE_PRIMARY = UniqueKeys0.KEY_COUNTRY_WISE_PRIMARY;
    public static final UniqueKey<LocationRecord> KEY_LOCATION_PRIMARY = UniqueKeys0.KEY_LOCATION_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AgeWiseCasesRecord, UInteger> IDENTITY_AGE_WISE_CASES = Internal.createIdentity(AgeWiseCases.AGE_WISE_CASES, AgeWiseCases.AGE_WISE_CASES.ID);
        public static Identity<CasesRecord, Integer> IDENTITY_CASES = Internal.createIdentity(Cases.CASES, Cases.CASES.ID);
        public static Identity<CountryWiseRecord, Integer> IDENTITY_COUNTRY_WISE = Internal.createIdentity(CountryWise.COUNTRY_WISE, CountryWise.COUNTRY_WISE.ID);
        public static Identity<LocationRecord, Integer> IDENTITY_LOCATION = Internal.createIdentity(Location.LOCATION, Location.LOCATION.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AgeWiseCasesRecord> KEY_AGE_WISE_CASES_PRIMARY = Internal.createUniqueKey(AgeWiseCases.AGE_WISE_CASES, "KEY_age_wise_cases_PRIMARY", AgeWiseCases.AGE_WISE_CASES.ID);
        public static final UniqueKey<CasesRecord> KEY_CASES_PRIMARY = Internal.createUniqueKey(Cases.CASES, "KEY_cases_PRIMARY", Cases.CASES.ID);
        public static final UniqueKey<CountryWiseRecord> KEY_COUNTRY_WISE_PRIMARY = Internal.createUniqueKey(CountryWise.COUNTRY_WISE, "KEY_country_wise_PRIMARY", CountryWise.COUNTRY_WISE.ID);
        public static final UniqueKey<LocationRecord> KEY_LOCATION_PRIMARY = Internal.createUniqueKey(Location.LOCATION, "KEY_location_PRIMARY", Location.LOCATION.ID);
    }
}
