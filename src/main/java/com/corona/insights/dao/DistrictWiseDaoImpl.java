package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.Tables;
import com.corona.insights.jooq.corona_insights.enums.CountryWiseSource;
import com.corona.insights.jooq.corona_insights.enums.DistrictWiseSource;
import com.corona.insights.jooq.corona_insights.tables.daos.DistrictWiseDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

@Repository
public class DistrictWiseDaoImpl extends DistrictWiseDao {

    public DistrictWiseDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public void removeOtherSourcesData() {
        DSL.using(configuration()).deleteFrom(Tables.DISTRICT_WISE).where(Tables.DISTRICT_WISE.SOURCE.eq(DistrictWiseSource.WORLDMETERS)).execute();
    }

}
