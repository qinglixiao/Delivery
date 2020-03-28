package me.std.db;

import org.greenrobot.greendao.query.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/8.
 */
public interface IDBAccess<T,K> {
    List<T> queryAll();

    T query(K key);

    QueryBuilder<T> queryBuilder();

    void insert(T entity);

    void insert(Iterable<T> entities);

    void insertOrReplace(T entity);

    void insertOrReplaceInTx(Iterable<T> entities);

    List<T> queryRaw(String where, String[] selectionArg);

    void update(T entity);

    void update(Iterable<T> entities);

    void delete(T entity);

    void deleteByKey(K key);

    void deleteByKeyInTx(K... keys);

    void deleteInTx(Iterable<T> entities);

    void deleteByKeyInTx(Iterable<K> keys);

    Long count();

    void execSQL(String sql, Object[] bindArgs) throws SQLException;
}
