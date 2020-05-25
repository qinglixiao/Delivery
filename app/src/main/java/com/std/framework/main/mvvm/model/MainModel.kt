package com.std.framework.main.mvvm.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.std.db.TpDatabaseHelper
import me.std.db.tables.Book

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/12.
 */
class MainModel() : MainContract {
    override var _books: MutableLiveData<List<Book>> = MutableLiveData()

    override suspend fun getBooks(): List<Book> {
        var b: List<Book> = listOf()
        withContext(Dispatchers.IO) {
            b = TpDatabaseHelper.getInstance(context).daoSet.book().allBooks()
        }
        return b
    }

    override suspend fun loadData() {
        withContext(Dispatchers.IO) {
            try {
                context?.assets?.open("books.json").use { inputStream ->
                    JsonReader(inputStream?.reader()).use { jsonReader ->
                        val plantType = object : TypeToken<List<Book>>() {}.type
                        val plantList: List<Book> = Gson().fromJson(jsonReader, plantType)
                        val database = TpDatabaseHelper.getInstance(context)
                        database.daoSet?.book()?.insertAll(plantList)
                    }
                }
            } catch (ex: Exception) {
                Log.d("LX", ex.message)
            }
        }
    }
}