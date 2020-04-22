package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.CountryWiseDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.CountryWise;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import static com.corona.insights.jooq.corona_insights.tables.CountryWise.COUNTRY_WISE;

@Repository
public class CountryWiseDaoImpl extends CountryWiseDao {

    public CountryWiseDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public void deleteForReportingDate(Date reportingDate, String country) {
        DSL.using(configuration()).deleteFrom(COUNTRY_WISE).where(COUNTRY_WISE.REPORTING_DATE.eq(reportingDate)).and(COUNTRY_WISE.COUNTRY.eq(country)).execute();
    }

}