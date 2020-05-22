package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.StateCodeNameMappingDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import static com.corona.insights.jooq.corona_insights.Tables.STATE_CODE_NAME_MAPPING;

@Repository
public class StateCodeNameMappingDaoImpl extends StateCodeNameMappingDao {

    public StateCodeNameMappingDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public String getCodeForStateName(String state) {
        return DSL.using(configuration()).select(STATE_CODE_NAME_MAPPING.CODE).from(STATE_CODE_NAME_MAPPING)
                .where(STATE_CODE_NAME_MAPPING.STATE.eq(state)).limit(1).fetchOneInto(String.class);
    }

}
