package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.StateWiseDao;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class StateWiseImplDao extends StateWiseDao {

    public StateWiseImplDao(Configuration configuration) {
        super(configuration);
    }

}
