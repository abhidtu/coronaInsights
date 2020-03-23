package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.CasesDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.Cases;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import static com.corona.insights.jooq.corona_insights.tables.Cases.CASES;

@Repository
public class CasesDaoImpl extends CasesDao {

    public CasesDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public void createOrUpdate(Cases cases) {
        DSL.using(configuration()).insertInto(CASES, CASES.LOCATION_ID, CASES.REPORTING_DATE, CASES.CONFIRMED, CASES.DEATHS, CASES.RECOVERED, CASES.FILE_NAME)
                .values(cases.getLocationId(), cases.getReportingDate(), cases.getConfirmed(), cases.getDeaths(), cases.getRecovered(), cases.getFileName())
                .onDuplicateKeyIgnore()
                .execute();
    }

}
