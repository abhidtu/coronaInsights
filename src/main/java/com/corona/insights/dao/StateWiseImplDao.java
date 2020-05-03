package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.enums.StateWiseSource;
import com.corona.insights.jooq.corona_insights.tables.daos.StateWiseDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import static com.corona.insights.jooq.corona_insights.tables.StateWise.STATE_WISE;

@Repository
public class StateWiseImplDao extends StateWiseDao {

    public StateWiseImplDao(Configuration configuration) {
        super(configuration);
    }

    public void removeOtherSourcesData() {
        DSL.using(configuration()).deleteFrom(STATE_WISE).where(STATE_WISE.SOURCE.eq(StateWiseSource.WORLDMETERS)).execute();
    }

}
