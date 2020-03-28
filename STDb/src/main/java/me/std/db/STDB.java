package me.std.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

import java.util.List;

import me.std.common.utils.AppContextUtil;
import me.std.db.tables.DaoSession;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/8.
 */
public class STDB {

    public static final String DEFAULT_DB = "std";
    private static STDB instance;
    /**
     * 数据库版本
     */
    private static int version = 1;

    private STDSQLiteOpenhelper helper;

    private Database db;

//    private SQLiteDatabase db;

    private static DaoSession session;

    private String dbId;

    private static List<Class> abstractDao;

    private void open(String dbId) {
        if (TextUtils.isEmpty(dbId)) {
            dbId = DEFAULT_DB;
        }
        String name = dbId + DbConfig.DB_SUFFIX;
        helper = new STDSQLiteOpenhelper(AppContextUtil.getAppContext(), name, null);
        db = helper.getWritableDb();
        session = new DaoBasicMaster(db, abstractDao).newSession();
    }

    /**
     * 创建用户数据库
     *
     * @param dbId 用户id
     */
    public synchronized static STDB create(String dbId, int version, List<Class> createTables) {
        STDB.version = version;
        STDB.abstractDao = createTables;
        return create(dbId);
    }

    /**
     * 创建用户数据库
     *
     * @param dbId 用户id
     */
    synchronized static STDB create(String dbId) {
        // 用户未登录调用，不创建数据库
        try {
            if (instance == null) {
                instance = new STDB();
                instance.dbId = dbId;
                instance.open(dbId);
            } else {
                // 老用户有可能创建过null为名的数据库，这里是对其清除
                if (instance.db != null && !TextUtils.isEmpty(dbId)
                        && !TextUtils.equals(dbId, instance.dbId)) {
                    AppContextUtil.getAppContext()
                            .deleteDatabase("null" + DbConfig.DB_SUFFIX);
                    close();
                    instance = new STDB();
                    instance.dbId = dbId;
                    instance.open(dbId);
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
            if (!TextUtils.isEmpty(instance.dbId)) {
                instance.dbId = null;
            }
            if (instance.helper != null)
                instance.helper.close();
            instance.helper = null;
            instance = null;
        }

        session = null;
    }

    public static String getDBName() {
        if (instance.helper != null)
            return instance.helper.getDatabaseName();
        return "";
    }

    public DaoSession getSession() {
        return session;
    }

    public Database getDatabase() {
        return db;
    }

    private static class STDSQLiteOpenhelper extends DatabaseOpenHelper {

        public STDSQLiteOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(Database db) {
            // TODO: 2017/2/27 这个操作？
            // DaoMaster.createAllTables(db, false);
            DaoBasicMaster.createAllTables(db, STDB.abstractDao, false);
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            try {
                // TODO: 2017/2/20 更新数据库方法
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
