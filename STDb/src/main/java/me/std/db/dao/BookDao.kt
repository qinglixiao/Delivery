package me.std.db.dao

import androidx.room.Dao
import androidx.room.Query
import me.std.db.core.BaseDao
import me.std.db.tables.Book

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/12.
 */
@Dao
interface BookDao : BaseDao<Book> {
    @Query("select * from book ")
    suspend fun allBooks(): List<Book>
}