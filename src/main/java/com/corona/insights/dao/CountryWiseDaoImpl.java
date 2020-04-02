package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.CountryWiseDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.CountryWise;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryWiseDaoImpl extends CountryWiseDao {

    public CountryWiseDaoImpl(Configuration configuration) {
        super(configuration);
    }

}