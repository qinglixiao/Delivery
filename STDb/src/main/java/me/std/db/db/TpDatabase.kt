package me.std.db.db

import DB_VERSION
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.std.db.comm.Converters
import me.std.db.dao.DaoSet
import me.std.db.tables.Book
import me.std.db.tables.Entity

/**
 * Description:数据库定义
 * Author: lixiao
 * Create on: 2020/4/28.
 */
@Database(
        entities =
        [Entity::class, Book::class],
        version = DB_VERSION,
        exportSchema = true
)
@TypeConverters(Converters::class)
internal abstract class TpDatabase : RoomDatabase(), DaoSet {

}