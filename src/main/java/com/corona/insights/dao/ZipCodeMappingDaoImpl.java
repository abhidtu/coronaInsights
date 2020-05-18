package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.ZipCodeMappingDao;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class ZipCodeMappingDaoImpl extends ZipCodeMappingDao {

    public ZipCodeMappingDaoImpl(Configuration configuration) {
        super(configuration);
    }
}
