package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.Tables;
import com.corona.insights.jooq.corona_insights.enums.CountryWiseSource;
import com.corona.insights.jooq.corona_insights.enums.DistrictWiseSource;
import com.corona.insights.jooq.corona_insights.tables.daos.DistrictWiseDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.DistrictWise;
import com.corona.insights.model.HardestHitDO;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import static com.corona.insights.jooq.corona_insights.tables.DistrictWise.DISTRICT_WISE;
import static org.jooq.impl.DSL.*;

@Repository
public class DistrictWiseDaoImpl extends DistrictWiseDao {

    public DistrictWiseDaoImpl(Configuration configuration) {
        super(configuration);
    }

    public void removeOtherSourcesData() {
        DSL.using(configuration()).deleteFrom(Tables.DISTRICT_WISE).where(Tables.DISTRICT_WISE.SOURCE.eq(DistrictWiseSource.WORLDMETERS)).execute();
    }

    public List<HardestHitDO> getHardestHitDistricts(BigDecimal latitude, BigDecimal longitude, int radius) {
        Field<BigDecimal> geometric_distance =
                (
                    field("6371", BigDecimal.class).multiply(acos(cos(rad(latitude))
                    .multiply(cos(rad(DISTRICT_WISE.LATITUDE)))
                    .multiply(cos(rad(DISTRICT_WISE.LONGITUDE).subtract(rad(longitude))))
                    .add(sin( rad(latitude)).multiply(DSL.sin(rad(DISTRICT_WISE.LATITUDE))))))
                )
        .as("geometric_distance");

        return DSL.using(configuration()).select(DSL.max(DISTRICT_WISE.CONFIRMED).as("confirmed"), DISTRICT_WISE.COUNTRY, DISTRICT_WISE.STATE, DISTRICT_WISE.DISTRICT, DISTRICT_WISE.LATITUDE, DISTRICT_WISE.LONGITUDE, geometric_distance).from(DISTRICT_WISE).groupBy(DISTRICT_WISE.DISTRICT, DISTRICT_WISE.COUNTRY, DISTRICT_WISE.STATE, DISTRICT_WISE.LATITUDE, DISTRICT_WISE.LONGITUDE)
                .having(geometric_distance.le(BigDecimal.valueOf(radius)))
                .orderBy(DSL.field("confirmed").desc()).fetchInto(HardestHitDO.class);
    }

    public DistrictWise getDistrict(String country, String state, String district) {
       return DSL.using(configuration()).selectFrom(DISTRICT_WISE).where(DISTRICT_WISE.COUNTRY.eq(country)).and(DISTRICT_WISE.STATE.eq(state)).and(DISTRICT_WISE.DISTRICT.eq(district)).limit(1).fetchOneInto(DistrictWise.class);
    }

}
