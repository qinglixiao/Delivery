
package com.std.framework.core.db;

import android.database.sqlite.SQLiteDatabase;

import com.std.framework.util.SharedPreferencesUtil;

import table.DaoSession;

public abstract class BaseDao<T extends Object> {
    protected InnerDB innerDB;

    public BaseDao(){
        connectionInnerDB();
    }

    public void connectionInnerDB() {
        innerDB = InnerDB.create(SharedPreferencesUtil.getUser());
    }

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
