package com.corona.insights.dao;

import com.corona.insights.jooq.corona_insights.tables.pojos.Properties;
import lombok.AllArgsConstructor;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.TableField;
import org.springframework.stereotype.Repository;

import static com.corona.insights.jooq.corona_insights.Tables.PROPERTIES;

@Repository
@AllArgsConstructor
public class DaoUtils {

    private PropertiesDaoImpl propertiesDao;
    private static String IS_DATA_UPDATING = "IS_DATA_UPDATING";
    private CountryWiseDaoImpl countryWiseDao;
    private StateWiseImplDao stateWiseImplDao;
    private DistrictWiseDaoImpl districtWiseDao;

    public static <R extends Record, T> Condition checkNull(TableField<R, T> tableField, T data) {
        if (data == null)
            return tableField.isNull();

        return tableField.eq(data);
    }

    public boolean isUpdateLocked() {
        Properties properties = propertiesDao.fetchOne(PROPERTIES.KEY, IS_DATA_UPDATING);
        return properties.getValue() != null && properties.getValue().equals("true");
    }

    public void acquireUpdateLock() {
        Properties properties = propertiesDao.fetchOne(PROPERTIES.KEY, IS_DATA_UPDATING);
        properties.setValue("true");
        propertiesDao.update(properties);
    }

    public void releaseUpdateLock() {
        Properties properties = propertiesDao.fetchOne(PROPERTIES.KEY, IS_DATA_UPDATING);
        properties.setValue("false");
        propertiesDao.update(properties);
    }

    public void removeOtherSourcesData() {
        countryWiseDao.removeOtherSourcesData();
        stateWiseImplDao.removeOtherSourcesData();
        districtWiseDao.removeOtherSourcesData();
    }

}