package com.corona.insights.dao;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.TableField;

public class DaoUtils {

    public static <R extends Record, T> Condition checkNull(TableField<R, T> tableField, T data) {
        if (data == null)
            return tableField.isNull();

        return tableField.eq(data);
    }

}