package com.xn.mars.common;


import java.util.Collections;

import org.apache.logging.log4j.nosql.appender.NoSqlObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * author:by Liang.qinjie
 * on 2017-03-04 12:38
 */

/**
 * The MongoDB implementation of {@link NoSqlObject}.
 */
public final class MongoDbObject implements NoSqlObject<BasicDBObject> {
    private final BasicDBObject mongoObject;

    public MongoDbObject() {
        this.mongoObject = new BasicDBObject();
    }

    @Override
    public void set(final String field, final Object value) {
        this.mongoObject.append(field, value);
    }

    @Override
    public void set(final String field, final NoSqlObject<BasicDBObject> value) {
        this.mongoObject.append(field, value.unwrap());
    }

    @Override
    public void set(final String field, final Object[] values) {
        final BasicDBList list = new BasicDBList();
        Collections.addAll(list, values);
        this.mongoObject.append(field, list);
    }

    @Override
    public void set(final String field, final NoSqlObject<BasicDBObject>[] values) {
        final BasicDBList list = new BasicDBList();
        for (final NoSqlObject<BasicDBObject> value : values) {
            list.add(value.unwrap());
        }
        this.mongoObject.append(field, list);
    }

    @Override
    public BasicDBObject unwrap() {
        return this.mongoObject;
    }
}