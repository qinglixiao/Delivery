package com.std.framework.main.mvvm.model

import androidx.lifecycle.MutableLiveData
import com.std.base.mvvm.model.DataContract
import me.std.db.tables.Book

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/5/13.
 */
interface MainContract : DataContract {
    var _books: MutableLiveData<List<Book>>
    suspend fun getBooks(): List<Book>
}