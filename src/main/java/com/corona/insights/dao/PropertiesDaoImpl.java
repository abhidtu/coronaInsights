package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.PropertiesDao;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class PropertiesDaoImpl extends PropertiesDao {

    public PropertiesDaoImpl(Configuration configuration) {
        super(configuration);
    }


}
