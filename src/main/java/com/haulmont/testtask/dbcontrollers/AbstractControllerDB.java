package com.haulmont.testtask.dbcontrollers;

import java.sql.*;
import java.util.List;

public abstract class AbstractControllerDB <E,K> {

    private JDBCSingleton db;

    AbstractControllerDB() {
        db = JDBCSingleton.getInstance();
    }

    protected abstract void addResultsToList(ResultSet rs, List<E> out) throws SQLException;
    public abstract List<E> getAll();
    public abstract E getEntityById(K id);
    public abstract void update(E entity);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);

    ResultSet sendQuery(String sql) throws SQLException {

        Statement statement = db.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        statement.close();
        return rs;
    }
}