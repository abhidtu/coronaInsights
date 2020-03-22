package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.daos.LocationDao;
import com.corona.insights.jooq.corona_insights.tables.pojos.Location;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import static com.corona.insights.dao.DaoUtils.checkNull;
import static com.corona.insights.jooq.corona_insights.tables.Location.LOCATION;

@Repository
public class LocationDaoImpl extends LocationDao {

    public LocationDaoImpl(Configuration configuration) {
        super(configuration);
    }

    private Integer find(Location location) {
        return DSL.using(configuration())
                .select(LOCATION.ID).from(LOCATION)
                .where(LOCATION.COUNTRY.eq(location.getCountry()))
                .and(checkNull(LOCATION.STATE, location.getState()))
                .and(checkNull(LOCATION.LATITUDE, location.getLatitude()))
                .and(checkNull(LOCATION.LONGITUDE, location.getLongitude()))
                .fetchOneInto(Integer.class);
    }

    public Integer createOrUpdate(Location location) {
        Integer id = find(location);
        if(id == null) {
            insert(location);
            return find(location);
        }
        return id;
    }

}