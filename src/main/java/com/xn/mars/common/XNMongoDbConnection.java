package com.xn.mars.common;


import com.mongodb.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.nosql.appender.AbstractNoSqlConnection;
import org.apache.logging.log4j.nosql.appender.NoSqlConnection;
import org.apache.logging.log4j.nosql.appender.NoSqlObject;
import org.apache.logging.log4j.status.StatusLogger;
import org.bson.BSON;
import org.bson.Transformer;


/**
 * author:by Liang.qinjie
 * on 2017-03-04 12:37
 */

/**
 * The MongoDB implementation of {@link NoSqlConnection}.
 */
public final class XNMongoDbConnection extends AbstractNoSqlConnection<BasicDBObject, MongoDbObject> {

    private static final Logger LOGGER = StatusLogger.getLogger();

    static {
        BSON.addEncodingHook(Level.class, new Transformer() {
            @Override
            public Object transform(final Object o) {
                if (o instanceof Level) {
                    return ((Level) o).name();
                }
                return o;
            }
        });
    }

    private final DBCollection collection;
    private final WriteConcern writeConcern;
    private final String clientName;

    public XNMongoDbConnection(final DB database, final WriteConcern writeConcern, final String collectionName, final String clientName) {
        this.collection = database.getCollection(collectionName);

        this.writeConcern = writeConcern;
        this.clientName = clientName;
    }

    @Override
    public MongoDbObject createObject() {
        return new MongoDbObject();
    }

    @Override
    public MongoDbObject[] createList(final int length) {
        return new MongoDbObject[length];
    }

    @Override
    public void insertObject(final NoSqlObject<BasicDBObject> object) {

        object.set("clientIP", NetUtils.getIp());
        object.set("clientName", this.clientName);
        try {
            this.collection.insert(object.unwrap(), this.writeConcern);

        } catch (final MongoException e) {
            throw new AppenderLoggingException("Failed to write log event to MongoDB due to error: " + e.getMessage(),
                    e);
        }
    }

    @Override
    public void closeImpl() {
        // LOG4J2-1196
        this.collection.getDB().getMongo().close();
    }

}