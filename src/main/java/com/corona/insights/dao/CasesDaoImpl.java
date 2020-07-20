package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.CasesDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.Cases;
import com.corona.insights.model.CoronaVirusETLMetricsDTO;
import org.jooq.Configuration;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static com.corona.insights.jooq.corona_insights.tables.Cases.CASES;
import static com.corona.insights.jooq.corona_insights.tables.Location.LOCATION;

@Repository
public class CasesDaoImpl extends CasesDao {

    public CasesDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public Cases getCase(Date reportingDate, Integer locationId) {
        return DSL.using(configuration()).selectFrom(CASES).where(CASES.LOCATION_ID.eq(locationId)).and(CASES.REPORTING_DATE.eq(reportingDate)).fetchOneInto(Cases.class);
    }

    public void createOrUpdate(Cases cases) {
        DSL.using(configuration()).insertInto(CASES, CASES.LOCATION_ID, CASES.REPORTING_DATE, CASES.CONFIRMED, CASES.DEATHS, CASES.RECOVERED, CASES.REPORTING_TIMESTAMP, CASES.FILE_NAME)
                .values(cases.getLocationId(), cases.getReportingDate(), cases.getConfirmed(), cases.getDeaths(), cases.getRecovered(), cases.getReportingTimestamp(), cases.getFileName())
                .onDuplicateKeyIgnore()
                .execute();
    }

    //select result.max_reporting_date as "time", sum(result.max_confirmed) as "confirmed cases", sum(result.max_deaths) as "death cases", sum(result.max_recovered) as "recovered cases" from (select max(c.`confirmed`) as max_confirmed, max(c.`deaths`) as max_deaths, max(c.`recovered`) as max_recovered, max(c.`reporting_date`) as max_reporting_date, l.`country` as country from cases c join `location` l on c.`location_id`=l.`id` where l.`country` = "US" group by c.`confirmed`, c.`reporting_date`, c.`location_id`) as result group by time order by time desc;
    //select max(c.`confirmed`) as max_confirmed, max(c.`deaths`) as max_deaths, max(c.`recovered`) as max_recovered, max(c.`reporting_date`) as max_reporting_date, l.`country` as country from cases c join `location` l on c.`location_id`=l.`id` where l.`country` = "US" group by c.`confirmed`, c.`reporting_date`, c.`location_id`
    public List<CoronaVirusETLMetricsDTO> aggregateDataForCountry(String country) {
        Table<?> aggregated = DSL.using(configuration()).select(DSL.max(CASES.CONFIRMED).as("aggregated_confirmed"), DSL.max(CASES.DEATHS).as("aggregated_deaths"), DSL.max(CASES.RECOVERED).as("aggregated_recovered"), DSL.max(CASES.REPORTING_DATE).as("aggregated_reporting_date"))
                                 .from(CASES.join(LOCATION).on(CASES.LOCATION_ID.eq(LOCATION.ID))).where(LOCATION.COUNTRY.eq(country)).groupBy(CASES.CONFIRMED, CASES.REPORTING_DATE, CASES.LOCATION_ID).asTable("aggregated");
        return DSL.using(configuration()).select(aggregated.field("aggregated_reporting_date").as("reportedDate").cast(Date.class), DSL.sum(aggregated.field("aggregated_confirmed").coerce(Integer.class)).as("confirmed"), DSL.sum(aggregated.field("aggregated_deaths").coerce(Integer.class)).as("deaths"), DSL.sum(aggregated.field("aggregated_recovered").coerce(Integer.class)).as("recovered"))
                                         .from(aggregated).groupBy(aggregated.field("aggregated_reporting_date")).orderBy(aggregated.field("aggregated_reporting_date").asc())
                                         .fetchInto(CoronaVirusETLMetricsDTO.class);
    }

    public List<CoronaVirusETLMetricsDTO> aggregateDataForCountryWithCutOffDate(String country, Timestamp cutOffDate) {
        Table<?> aggregated = DSL.using(configuration()).select(DSL.max(CASES.CONFIRMED).as("aggregated_confirmed"), DSL.max(CASES.DEATHS).as("aggregated_deaths"), DSL.max(CASES.RECOVERED).as("aggregated_recovered"), DSL.max(CASES.REPORTING_DATE).as("aggregated_reporting_date"))
                .from(CASES.join(LOCATION).on(CASES.LOCATION_ID.eq(LOCATION.ID))).where(LOCATION.COUNTRY.eq(country)).and(CASES.CREATED_AT.gt(cutOffDate)).groupBy(CASES.CONFIRMED, CASES.REPORTING_DATE, CASES.LOCATION_ID).asTable("aggregated");
        return DSL.using(configuration()).select(aggregated.field("aggregated_reporting_date").as("reportedDate").cast(Date.class), DSL.sum(aggregated.field("aggregated_confirmed").coerce(Integer.class)).as("confirmed"), DSL.sum(aggregated.field("aggregated_deaths").coerce(Integer.class)).as("deaths"), DSL.sum(aggregated.field("aggregated_recovered").coerce(Integer.class)).as("recovered"))
                .from(aggregated).groupBy(aggregated.field("aggregated_reporting_date")).orderBy(aggregated.field("aggregated_reporting_date").asc())
                .fetchInto(CoronaVirusETLMetricsDTO.class);
    }

    public List<CoronaVirusETLMetricsDTO> aggregateDataForState(String state) {
        Table<?> aggregated = DSL.using(configuration()).select(DSL.max(CASES.CONFIRMED).as("aggregated_confirmed"), DSL.max(CASES.DEATHS).as("aggregated_deaths"), DSL.max(CASES.RECOVERED).as("aggregated_recovered"), DSL.max(CASES.REPORTING_DATE).as("aggregated_reporting_date"))
                .from(CASES.join(LOCATION).on(CASES.LOCATION_ID.eq(LOCATION.ID))).where(LOCATION.STATE.eq(state)).groupBy(CASES.CONFIRMED, CASES.REPORTING_DATE, CASES.LOCATION_ID).asTable("aggregated");
        return DSL.using(configuration()).select(aggregated.field("aggregated_reporting_date").as("reportedDate").cast(Date.class), DSL.sum(aggregated.field("aggregated_confirmed").coerce(Integer.class)).as("confirmed"), DSL.sum(aggregated.field("aggregated_deaths").coerce(Integer.class)).as("deaths"), DSL.sum(aggregated.field("aggregated_recovered").coerce(Integer.class)).as("recovered"))
                .from(aggregated).groupBy(aggregated.field("aggregated_reporting_date")).orderBy(aggregated.field("aggregated_reporting_date").asc())
                .fetchInto(CoronaVirusETLMetricsDTO.class);
    }

    public List<CoronaVirusETLMetricsDTO> aggregateDataForStateWithCutOffDate(String state, Timestamp cutOffDate) {
        Table<?> aggregated = DSL.using(configuration()).select(DSL.max(CASES.CONFIRMED).as("aggregated_confirmed"), DSL.max(CASES.DEATHS).as("aggregated_deaths"), DSL.max(CASES.RECOVERED).as("aggregated_recovered"), DSL.max(CASES.REPORTING_DATE).as("aggregated_reporting_date"))
                .from(CASES.join(LOCATION).on(CASES.LOCATION_ID.eq(LOCATION.ID))).where(LOCATION.STATE.eq(state)).and(CASES.CREATED_AT.gt(cutOffDate)).groupBy(CASES.CONFIRMED, CASES.REPORTING_DATE, CASES.LOCATION_ID).asTable("aggregated");
        return DSL.using(configuration()).select(aggregated.field("aggregated_reporting_date").as("reportedDate").cast(Date.class), DSL.sum(aggregated.field("aggregated_confirmed").coerce(Integer.class)).as("confirmed"), DSL.sum(aggregated.field("aggregated_deaths").coerce(Integer.class)).as("deaths"), DSL.sum(aggregated.field("aggregated_recovered").coerce(Integer.class)).as("recovered"))
                .from(aggregated).groupBy(aggregated.field("aggregated_reporting_date")).orderBy(aggregated.field("aggregated_reporting_date").asc())
                .fetchInto(CoronaVirusETLMetricsDTO.class);
    }

    public List<CoronaVirusETLMetricsDTO> aggregateDataForDestrict(BigDecimal latitude, BigDecimal longitude) {
        Table<?> aggregated = DSL.using(configuration()).select(DSL.max(CASES.CONFIRMED).as("aggregated_confirmed"), DSL.max(CASES.DEATHS).as("aggregated_deaths"), DSL.max(CASES.RECOVERED).as("aggregated_recovered"), DSL.max(CASES.REPORTING_DATE).as("aggregated_reporting_date"))
                .from(CASES.join(LOCATION).on(CASES.LOCATION_ID.eq(LOCATION.ID))).where(LOCATION.LATITUDE.eq(latitude)).and(LOCATION.LONGITUDE.eq(longitude)).groupBy(CASES.CONFIRMED, CASES.REPORTING_DATE, CASES.LOCATION_ID).asTable("aggregated");
        return DSL.using(configuration()).select(aggregated.field("aggregated_reporting_date").as("reportedDate").cast(Date.class), DSL.sum(aggregated.field("aggregated_confirmed").coerce(Integer.class)).as("confirmed"), DSL.sum(aggregated.field("aggregated_deaths").coerce(Integer.class)).as("deaths"), DSL.sum(aggregated.field("aggregated_recovered").coerce(Integer.class)).as("recovered"))
                .from(aggregated).groupBy(aggregated.field("aggregated_reporting_date")).orderBy(aggregated.field("aggregated_reporting_date").desc())
                .fetchInto(CoronaVirusETLMetricsDTO.class);
    }

    public List<CoronaVirusETLMetricsDTO> aggregateDataForDestrictWithCutOffDate(BigDecimal latitude, BigDecimal longitude, Timestamp cutOffDate) {
        Table<?> aggregated = DSL.using(configuration()).select(DSL.max(CASES.CONFIRMED).as("aggregated_confirmed"), DSL.max(CASES.DEATHS).as("aggregated_deaths"), DSL.max(CASES.RECOVERED).as("aggregated_recovered"), DSL.max(CASES.REPORTING_DATE).as("aggregated_reporting_date"))
                .from(CASES.join(LOCATION).on(CASES.LOCATION_ID.eq(LOCATION.ID))).where(LOCATION.LATITUDE.eq(latitude)).and(LOCATION.LONGITUDE.eq(longitude)).and(CASES.CREATED_AT.gt(cutOffDate)).groupBy(CASES.CONFIRMED, CASES.REPORTING_DATE, CASES.LOCATION_ID).asTable("aggregated");
        return DSL.using(configuration()).select(aggregated.field("aggregated_reporting_date").as("reportedDate").cast(Date.class), DSL.sum(aggregated.field("aggregated_confirmed").coerce(Integer.class)).as("confirmed"), DSL.sum(aggregated.field("aggregated_deaths").coerce(Integer.class)).as("deaths"), DSL.sum(aggregated.field("aggregated_recovered").coerce(Integer.class)).as("recovered"))
                .from(aggregated).groupBy(aggregated.field("aggregated_reporting_date")).orderBy(aggregated.field("aggregated_reporting_date").desc())
                .fetchInto(CoronaVirusETLMetricsDTO.class);
    }

    public Map<Date, List<Integer>> fetchReportingDateAggregatedLocationWise() {
        return DSL.using(configuration()).select(CASES.REPORTING_DATE, CASES.LOCATION_ID).from(CASES).orderBy(CASES.REPORTING_DATE.asc()).fetchGroups(CASES.REPORTING_DATE, CASES.LOCATION_ID);
    }

    public List<Date> getDistinctReportingDates() {
        return DSL.using(configuration()).selectDistinct(CASES.REPORTING_DATE).from(CASES).orderBy(CASES.REPORTING_DATE.asc()).fetchInto(Date.class);
    }

    public List<Integer> getLocationIdsForReportingDate(Date reportingDate) {
        return DSL.using(configuration()).selectDistinct(CASES.LOCATION_ID).from(CASES).where(CASES.REPORTING_DATE.eq(reportingDate)).fetchInto(Integer.class);
    }

    public Cases fetchCaseByReportingDateAndLocationId(Date reportingDate, Integer locationId) {
        return DSL.using(configuration()).select().from(CASES).where(CASES.REPORTING_DATE.eq(reportingDate))
                                                       .and(CASES.LOCATION_ID.eq(locationId)).limit(1).fetchOneInto(Cases.class);
    }

    public Timestamp computeCutOfDate() {
        return DSL.using(configuration()).select(CASES.CREATED_AT).from(CASES).orderBy(CASES.CREATED_AT.desc()).limit(1).fetchOneInto(Timestamp.class);
    }

}
