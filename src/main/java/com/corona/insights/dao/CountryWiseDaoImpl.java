package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.Tables;
import com.corona.insights.jooq.corona_insights.enums.CountryWiseSource;
import com.corona.insights.jooq.corona_insights.tables.daos.CountryWiseDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.sql.Date;

import static com.corona.insights.jooq.corona_insights.tables.CountryWise.COUNTRY_WISE;

@Repository
public class CountryWiseDaoImpl extends CountryWiseDao {

    public CountryWiseDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public void deleteForReportingDate(Date reportingDate) {
        DSL.using(configuration()).deleteFrom(COUNTRY_WISE).where(COUNTRY_WISE.REPORTING_DATE.eq(reportingDate)).execute();
    }

    public void removeOtherSourcesData() {
        DSL.using(configuration()).deleteFrom(Tables.COUNTRY_WISE).where(COUNTRY_WISE.SOURCE.eq(CountryWiseSource.WORLDMETERS)).execute();
    }

}