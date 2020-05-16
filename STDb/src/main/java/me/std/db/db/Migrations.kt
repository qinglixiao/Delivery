package com.tope.db.db

import androidx.room.migration.Migration

/**
 * =====================================================
 * 数据库升级历史
 *
 * !!!注意
 * 升级SQL语句对字段类型的定义一定要与实体类型一致，否则会导致升级失败
 * 对于NOT NULL、DEFAULT... 要尤其注意
 * ======================================================
 */

/**
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'user' ADD COLUMN 'address' TEXT")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'user' ADD COLUMN 'tel' TEXT")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'user' ADD COLUMN 'country' TEXT")
    }
}
**/

class Migrations {
    companion object {
        //将变更历史加入集合
        var versions: Array<Migration> = arrayOf()
    }
}
