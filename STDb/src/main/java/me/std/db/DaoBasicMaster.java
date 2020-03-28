package me.std.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.std.db.tables.DaoMaster;
import me.std.db.tables.DaoSession;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/3/8.
 */
public class DaoBasicMaster extends DaoMaster {
    private static List<Class> daoClassList;

    public DaoBasicMaster(Database db, List<Class> abstractDaoList) {
        super(db);
        daoClassList = abstractDaoList;
    }

    public DaoBasicMaster(Database db) {
        super(db);
    }

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        if (daoClassList == null) {
            return;
        }
        for (Class clazz : daoClassList) {
            try {
                Method createTable = clazz.getMethod("createTable", Database.class, boolean.class);
                createTable.invoke(db, ifNotExists);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createAllTables(Database db, List<Class> daoClassList, boolean ifNotExists) {
        if (daoClassList == null) {
            return;
        }
        for (Class clazz : daoClassList) {
            try {
                Method createTable = clazz.getMethod("createTable", Database.class, boolean.class);
                createTable.invoke(clazz, db, ifNotExists);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        if (daoClassList == null) {
            return;
        }
        for (Class clazz : daoClassList) {
            try {
                Method createTable = clazz.getMethod("dropTable", Database.class, boolean.class);
                createTable.invoke(db, ifExists);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoBasicMaster daoMaster = new DaoBasicMaster(db);
        return daoMaster.newSession();
    }

    public boolean contains(Class cls) {
        return daoClassList.contains(cls);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
}
