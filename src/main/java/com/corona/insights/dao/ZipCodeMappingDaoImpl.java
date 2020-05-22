package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.ZipCodeMappingDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.corona.insights.jooq.corona_insights.Tables.ZIP_CODE_MAPPING;

@Repository
public class ZipCodeMappingDaoImpl extends ZipCodeMappingDao {

    public ZipCodeMappingDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public List<Integer> getZipCodesForStateAndDistrict(String stateCode, String district) {
        return DSL.using(configuration()).select(ZIP_CODE_MAPPING.ZIP).from(ZIP_CODE_MAPPING)
                                  .where(ZIP_CODE_MAPPING.STATE.eq(stateCode)).and(ZIP_CODE_MAPPING.CITY.eq(district)).fetchInto(Integer.class);
    }

}
