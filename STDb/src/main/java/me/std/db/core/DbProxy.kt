package me.std.db.core

import android.database.Cursor
import android.util.Log
import me.std.db.dao.DaoSet
import me.std.db.db.TpDatabase
import java.lang.Exception

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/4/28.
 */
class DbProxy : IDbAccess {
    val TAG: String = "db"
    internal val db: TpDatabase

    override val daoSet: DaoSet
        get() = db

    internal constructor(db: TpDatabase) {
        this.db = db
    }

    override fun exeSQL(sql: String, args: Array<Any>?, result: (Cursor?) -> Unit) {
        Log.d(TAG, "SQL: " + sql)
        if (isSqlSafe(sql)) {
            var cursor: Cursor? = null
            try {
                cursor = db.query(sql, args)
                result(cursor)
            } catch (ex: Exception) {
                print(ex.stackTrace)
            } finally {
                cursor?.close()
                cursor = null
            }
        } else {
            result(null)
        }
    }

    override fun close() {
        db.close()
    }

    fun isSqlSafe(sql: String): Boolean {
        //TODO logic
        if (sql.contains("DROP")) {
            return false
        }
        return true
    }
}