package me.std.db.sample

import android.content.Context
import android.database.Cursor
import me.std.db.TpDatabaseHelper
import me.std.db.tables.Entity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Description: 示例
 * Author: lixiao
 * Create on: 2020/5/2.
 */
class UseDemo {
    fun call(context: Context) {
        TpDatabaseHelper.getInstance(context).daoSet.entity().let { entityDao ->
            GlobalScope.launch {
                entityDao.insert(Entity(1, "张三"))
                entityDao.getEntity("张三")
                entityDao.allEntity()
            }
        }
    }

    fun exeSQL(context: Context) {
        TpDatabaseHelper.getInstance(context)
                .exeSQL("select * from user", null) { cursor: Cursor? ->
                    while (cursor?.moveToNext()!!) {
                        //查询...
                    }
                }
    }
}