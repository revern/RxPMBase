package com.example.revern.rxpmbase.utils.storage

import java.lang.reflect.Type

interface IStorage {

    fun <T> put(key: String, item: T)

    fun <T> get(key: String, type: Type): T?

    fun putString(key: String, string: String)

    fun getString(key: String): String?

    fun putLong(key: String, value: Long)

    fun getLong(key: String, defaultValue: Long): Long

    fun putInt(key: String, value: Int)

    fun getInt(key: String, defaultValue: Int): Int

    fun putBoolen(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun <T> putCollection(key: String, items: List<T>)

    fun <T> getCollection(key: String, type: Type): List<T>?

    fun remove(key: String)

}
