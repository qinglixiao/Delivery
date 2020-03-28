package me.std.db;

import org.greenrobot.greendao.query.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/9.
 */
public class DbManager<T, K> extends BaseDao implements IDBAccess<T, K> {
    private static DbManager instance;
    private Class entity;

    private volatile IDBAccess<T, K> mDbAccess;

    public static DbManager getInstance(Class entity) {
        if (instance == null) {
            instance = new DbManager(entity);
        }
        instance.connectionToonDB();
        return instance;
    }

    private DbManager(Class entity) {
        this.entity = entity;
    }

    @Override
    public void initAccess() {
        mDbAccess = new GreenDaoAccess(getDao(entity));
    }

    @Override
    public List<T> queryAll() {
        return mDbAccess.queryAll();
    }

    @Override
    public T query(K key) {
        return mDbAccess.query(key);
    }

    @Override
    public QueryBuilder<T> queryBuilder() {
        return mDbAccess.queryBuilder();
    }

    @Override
    public void insert(T entity) {
        mDbAccess.insert(entity);
    }

    @Override
    public void insert(Iterable<T> entities) {
        mDbAccess.insert(entities);
    }

    @Override
    public void insertOrReplace(T entity) {
        mDbAccess.insertOrReplace(entity);
    }

    @Override
    public void insertOrReplaceInTx(Iterable<T> entities) {
        mDbAccess.insertOrReplaceInTx(entities);
    }

    @Override
    public List<T> queryRaw(String where, String[] selectionArg) {
        return mDbAccess.queryRaw(where, selectionArg);
    }

    @Override
    public void update(T entity) {
        mDbAccess.update(entity);
    }

    @Override
    public void update(Iterable<T> entities) {
        mDbAccess.update(entities);
    }

    @Override
    public void delete(T entity) {
        mDbAccess.delete(entity);
    }

    @Override
    public void deleteByKey(K key) {
        mDbAccess.deleteByKey(key);
    }

    @Override
    public void deleteByKeyInTx(K... keys) {
        mDbAccess.deleteByKeyInTx(keys);
    }

    @Override
    public void deleteInTx(Iterable<T> entities) {
        mDbAccess.deleteInTx(entities);
    }

    @Override
    public void deleteByKeyInTx(Iterable<K> keys) {
        mDbAccess.deleteByKeyInTx(keys);
    }

    @Override
    public Long count() {
        return mDbAccess.count();
    }

    @Override
    public void execSQL(String sql, Object[] bindArgs) throws SQLException {
        mDbAccess.execSQL(sql, bindArgs);
    }
}
