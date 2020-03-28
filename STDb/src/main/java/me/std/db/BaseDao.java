package me.std.db;

import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

import me.std.db.tables.DaoSession;
import me.std.db.tables.SportLocation;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/8.
 */
public abstract class BaseDao {
    private STDB stDB;

    public void connectionToonDB() {
        stDB = STDB.create(null, 1, getUnEncryptDBList());
        if (stDB != null && stDB.getDatabase() != null) {
            initAccess();
        }
    }

    private List<Class> getUnEncryptDBList() {
        List tables = new ArrayList();
        tables.add(SportLocation.class);
        addTable(tables);
        return tables;
    }

    public void addTable(List<Class> tables) {

    }

    public DaoSession getSession(Class daoClass) {
        DaoSession session = null;
        try {
            session = stDB.getSession();
        } catch (Exception e) {
        }
        return session;
    }

    protected AbstractDao getDao(Class daoClass) {
        return getSession(daoClass).getDao(daoClass);
    }

    public Database getDatabase(Class daoClass) {
        Database db = null;
        try {
            db = stDB.getDatabase();
        } catch (Exception e) {
        }
        return db;
    }

    public abstract void initAccess();
}
