
package com.std.framework.core.db;

import android.database.sqlite.SQLiteDatabase;

import com.std.framework.util.SharedPreferencesUtil;

import db.table.DaoSession;

public abstract class BaseDao {
    protected InnerDB innerDB;

    public void connectionInnerDB() {
        innerDB = InnerDB.create(SharedPreferencesUtil.getUser());
        if (innerDB != null && innerDB.getDatabase() != null) {
            initAccess();
        }
    }

    public abstract void initAccess();

    public DaoSession getSession(){
        if(innerDB != null){
            return innerDB.getSession();
        }
        return null;
    }

    public SQLiteDatabase getDatabase(){
        if(innerDB != null){
            return innerDB.getDatabase();
        }
        return null;
    }

}
