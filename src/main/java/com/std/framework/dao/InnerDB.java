
package com.std.framework.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.cn.sale.dao.entity.DaoMaster;
import com.cn.sale.dao.entity.DaoSession;
import com.std.framework.basic.App;

public class InnerDB {
    private static InnerDB instance;

    /**
     * 数据库统一后缀名
     */
    private static final String DATABASE_NAME_SUFFIX = "_inner.db";

    /**
     * 数据库版本
     */

    public static final int VERSION = 1;

    private static InnerSQLiteOpenhelper helper;

    private SQLiteDatabase db;

    private static DaoSession session;

    private String userId;

    private void open(String userId) {
        String name = userId + DATABASE_NAME_SUFFIX;
        helper = new InnerSQLiteOpenhelper(App.instance, name, null);
        db = helper.getWritableDatabase();
        session = new DaoMaster(db).newSession();
    }

    /**
     * 创建用户数据库
     *
     * @param userId 用户id
     */
    public synchronized static InnerDB create(String userId) {
        try {
            if (instance == null) {
                instance = new InnerDB();
                instance.userId = userId;
                instance.open(userId);
            } else {
                // 老用户有可能创建过null为名的数据库，这里是对其清除
                if (instance.db != null && !TextUtils.isEmpty(userId)
                        && !TextUtils.equals(userId, instance.userId)) {
                    App.instance.deleteDatabase("null" + DATABASE_NAME_SUFFIX);
                    close();
                    instance = new InnerDB();
                    instance.userId = userId;
                    instance.open(userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    public static void close() {
        if (instance != null) {
            if (instance.db != null) {
                instance.db.close();
            }
            if (!TextUtils.isEmpty(instance.userId)) {
                instance.userId = null;
            }
            instance = null;
        }
        if (helper != null)
            helper.close();
        helper = null;
        session = null;
    }

    public DaoSession getSession() {
        return session;
    }

    static class InnerSQLiteOpenhelper extends SQLiteOpenHelper {

        public InnerSQLiteOpenhelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            DaoMaster.createAllTables(db, false);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
