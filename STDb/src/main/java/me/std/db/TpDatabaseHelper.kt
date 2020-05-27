package me.std.db

import DATABASE_NAME
import DB_VERSION
import DB_VER_NEW_KEY
import DB_VER_OLD_KEY
import DB_VER_PREF
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.std.db.core.DbProxy
import me.std.db.core.IDbAccess
import com.tope.db.db.Migrations
import me.std.db.db.TpDatabase

/**
 * Description: 操作数据库入口类
 * Author: lixiao
 * Create on: 2020/4/28.
 */
class TpDatabaseHelper {
    companion object {
        @Volatile
        private var instance: IDbAccess? = null
        fun getInstance(context: Context): IDbAccess {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): IDbAccess {
            return DbProxy(
                    with(
                            Room.databaseBuilder(
                                    context.applicationContext,
                                    TpDatabase::class.java,
                                    DATABASE_NAME
                            )
                    ) {
                        addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                            }

                            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                                super.onDestructiveMigration(db)
                                with(context.getSharedPreferences(DB_VER_PREF, Context.MODE_PRIVATE)) {
                                    edit().putInt(DB_VER_NEW_KEY, getInt(DB_VER_OLD_KEY, 0)).apply()
                                }
                            }
                        })

                        if (checkUpdate(context)) {
                            addMigrations(*getMigrations())
                            fallbackToDestructiveMigration()
                        }
                        build()
                    }
            )
        }

        private fun getMigrations(): Array<Migration> {
            return Migrations.versions
        }

        private fun checkUpdate(context: Context): Boolean {
            val sp: SharedPreferences =
                    context.getSharedPreferences(DB_VER_PREF, Context.MODE_PRIVATE)
            var old = sp.getInt(DB_VER_NEW_KEY, 0)
            val new = DB_VERSION
            if (old != new) {
                sp.edit().putInt(DB_VER_NEW_KEY, DB_VERSION).apply()
                sp.edit().putInt(DB_VER_OLD_KEY, old).apply()
                if (old == 0) {
                    old = DB_VERSION
                }
            }
            return old < new
        }
    }
}