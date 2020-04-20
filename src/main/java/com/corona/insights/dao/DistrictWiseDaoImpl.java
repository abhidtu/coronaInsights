package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.DistrictWiseDao;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class DistrictWiseDaoImpl extends DistrictWiseDao {

    public DistrictWiseDaoImpl(Configuration configuration) {
        super(configuration);
    }
}
