package me.std.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.std.db.core.BaseDao
import me.std.db.tables.Entity

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/4/28.
 */
@Dao
interface EntityDao : BaseDao<Entity> {

    @Query("SELECT * FROM entity WHERE name = :name")
    fun getEntity(name: String): LiveData<Entity>

    @Query("SELECT * FROM entity")
    fun allEntity(): List<Entity>
}