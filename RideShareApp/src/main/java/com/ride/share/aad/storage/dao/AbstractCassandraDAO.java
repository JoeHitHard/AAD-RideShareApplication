package com.ride.share.aad.storage.dao;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.ride.share.aad.storage.service.CassandraStorageService;


public abstract class AbstractCassandraDAO<T> implements DataAccessObject<T>{

    private final CqlSession cqlSession;

    public AbstractCassandraDAO() {
        cqlSession = CassandraStorageService.getCqlSession();
    }

    public CqlSession getCqlSession() {
        return cqlSession;
    }

    @Override
    public ResultSet createTable() {
        return bindAndExecute(new Object[1], getCreateStmt());
    }

    private ResultSet bindAndExecute(Object[] values, PreparedStatement createStmt) {
        BoundStatement bind = createStmt.bind(values);
        return cqlSession.execute(bind);
    }

    @Override
    public ResultSet insert(String key, Object... values) {
        return bindAndExecute(values, getInsertStmt());
    }

    @Override
    public ResultSet update(String key, Object... values) {
        return bindAndExecute(values, getUpdateStmt());
    }

    @Override
    public ResultSet get(String key) {
        return bindAndExecute(new String[]{key}, getStmt());
    }

    @Override
    public ResultSet delete(String key) {
        return bindAndExecute(new String[]{key}, getDeleteStmt());
    }
}
