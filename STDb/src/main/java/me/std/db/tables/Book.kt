package me.std.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/12.
 */
@Entity(tableName = "book")
data class Book(
        val name: String? = null,
        val author: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0
    var price: Float? = 0.0f
    var description: String? = null
    var imageUrl: String? = null

}