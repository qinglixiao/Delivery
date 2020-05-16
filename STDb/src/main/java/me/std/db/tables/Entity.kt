package me.std.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/4/28.
 */
@Entity(tableName = "entity")
data class Entity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,
        val name: String

) {
    var address: String? = null
    var tel: String? = null
    var country: String? = null
    var author: String? = null
    var price: Float? = 0.0f
}