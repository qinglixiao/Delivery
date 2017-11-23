package com.std.framework.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.std.framework.core.Logger;
import com.std.framework.util.AppUtil;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

import db.table.DaoMaster;
import db.table.DaoSession;

/**
 * Description:
 * Created by 李晓 ON 2017/11/23.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class FrameDB {
    private static FrameDB instance;

    /**
     * 数据库统一后缀名
     */
    public static final String DATABASE_NAME_SUFFIX = "_frame.db";

    private static final int DEFAULT_VERSION = 1;

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static ToonSQLiteOpenhelper helper;

    private Database db;

    private SQLiteDatabase sdb;

    private static DaoSession session;

    private String userId;

    private void open(String userId) {
        String name = userId + DATABASE_NAME_SUFFIX;
        helper = new ToonSQLiteOpenhelper(AppUtil.getAppContext(), name, null);
        db = helper.getEncryptedWritableDb("");
        sdb = (SQLiteDatabase)db.getRawDatabase();
        session = new DaoMaster(db).newSession();
    }

    /**
     * 创建用户数据库
     *
     * @param userId 用户id
     */
    public synchronized static FrameDB create(String userId) {
        // 用户未登录调用，不创建数据库
        try {
            if (instance == null) {
                instance = new FrameDB();
                instance.userId = userId;
                instance.open(userId);
            } else {
                // 老用户有可能创建过null为名的数据库，这里是对其清除
                if (instance.db != null && !TextUtils.isEmpty(userId)
                        && !TextUtils.equals(userId, instance.userId)) {
                    AppUtil.getAppContext().deleteDatabase("null" + DATABASE_NAME_SUFFIX);
                    close();
                    instance = new FrameDB();
                    instance.userId = userId;
                    instance.open(userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("database", e + "Database create Exception");
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
        if (helper != null) {
            helper.close();
        }
        helper = null;
        session = null;
    }

    public static String getDBName(){
        if(helper != null) {
            return helper.getDatabaseName();
        }
        return "";
    }

    public DaoSession getSession() {
        return session;
    }

    public SQLiteDatabase getDatabase() {
        if(sdb == null || sdb.isOpen() == false){
            create(userId);
            sdb = (SQLiteDatabase)db.getRawDatabase();
        }
        return sdb;
    }

    static class ToonSQLiteOpenhelper extends DatabaseOpenHelper {

        public ToonSQLiteOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, VERSION);
        }

        @Override
        public void onCreate(Database db) {
            DaoMaster.createAllTables(db, false);
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            try {
                while (++oldVersion <= newVersion) {
                    if (oldVersion <= DEFAULT_VERSION) {
                        // 全量更新
                        return;
                    }
                    switch (oldVersion) {
                        // 增加记录消息操作的表
                        // 最近会话和最近通知增加消息id
                        case DEFAULT_VERSION + 1:
                            break;
                        case DEFAULT_VERSION + 2:
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
