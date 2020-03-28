package me.std.db;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/8.
 */
public class GreenDaoAccess<T, K> implements IDBAccess<T, K> {
    private AbstractDao<T, K> dao;

    public GreenDaoAccess(AbstractDao<T, K> dao) {
        this.dao = dao;
    }

    @Override
    public void insert(T entity) {
        synchronized (AbstractDao.class) {
            try {
                dao.insert(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(Iterable<T> entities) {
        synchronized (AbstractDao.class) {
            dao.insertInTx(entities);
        }
    }

    @Override
    public synchronized void insertOrReplace(T entity) {
        synchronized (AbstractDao.class) {
            dao.insertOrReplace(entity);
        }
    }

    @Override
    public synchronized void insertOrReplaceInTx(Iterable<T> entities) {
        synchronized (AbstractDao.class) {
            dao.insertOrReplaceInTx(entities);
        }
    }

    @Override
    public List<T> queryAll() {
        synchronized (AbstractDao.class) {
            return dao.loadAll();
        }
    }

    @Override
    public T query(K key) {
        synchronized (AbstractDao.class) {
            return dao.load(key);
        }
    }

    @Override
    public List<T> queryRaw(String where, String[] selectionArg) {
        synchronized (AbstractDao.class) {
            return dao.queryRaw(where, selectionArg);
        }
    }

    @Override
    public QueryBuilder<T> queryBuilder() {
        synchronized (AbstractDao.class) {
            return dao.queryBuilder();
        }
    }

    @Override
    public void delete(T entity) {
        synchronized (AbstractDao.class) {
            dao.delete(entity);
        }
    }

    @Override
    public void deleteByKey(K key) {
        synchronized (AbstractDao.class) {
            dao.deleteByKey(key);
        }
    }

    @Override
    public void deleteByKeyInTx(K... keys) {
        synchronized (AbstractDao.class) {
            dao.deleteByKeyInTx(keys);
        }
    }

    @Override
    public void deleteByKeyInTx(Iterable<K> keys) {
        synchronized (AbstractDao.class) {
            dao.deleteByKeyInTx(keys);
        }
    }

    @Override
    public void deleteInTx(Iterable<T> entities) {
        synchronized (AbstractDao.class) {
            dao.deleteInTx(entities);
        }
    }

    @Override
    public Long count() {
        synchronized (AbstractDao.class) {
            return dao.count();
        }
    }

    @Override
    public void execSQL(String sql, Object[] bindArgs) throws SQLException {
        synchronized (AbstractDao.class) {
            dao.getDatabase().execSQL(sql, bindArgs);
        }
    }

    @Override
    public void update(T entity) {
        synchronized (AbstractDao.class) {
            dao.update(entity);
        }
    }

    @Override
    public void update(Iterable<T> entities) {
        synchronized (AbstractDao.class) {
            dao.updateInTx(entities);
        }
    }
}
