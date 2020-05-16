package me.std.db.core

import android.database.Cursor
import me.std.db.dao.DaoSet

/**
 * Description: 数据库对外公布的接口
 * Author: lixiao
 * Create on: 2020/4/28.
 */
interface IDbAccess {
    val daoSet: DaoSet
    fun exeSQL(sql: String, args: Array<Any>?, result: (Cursor?) -> Unit)
    fun close()
}