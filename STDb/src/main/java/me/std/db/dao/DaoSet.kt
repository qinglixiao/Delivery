package me.std.db.dao

/**
 * Description: 所有dao在此注册
 * Author: lixiao
 * Create on: 2020/4/29.
 */
interface DaoSet {
    fun entity(): EntityDao
    fun book(): BookDao
    //...
}