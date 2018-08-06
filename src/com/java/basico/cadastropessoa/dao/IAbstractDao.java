package com.java.basico.cadastropessoa.dao;

import java.sql.SQLException;
import java.util.List;

public interface IAbstractDao<T> {
    List<T> all() throws SQLException;
    T findById(int id) throws SQLException;
    void insert (T entidade) throws SQLException;
    void update (T entidade) throws SQLException;
    void delete(T entidade) throws SQLException;
}
