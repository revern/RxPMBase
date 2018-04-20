package com.example.revern.rxpmbase.utils.storage

import android.content.SharedPreferences
import com.example.revern.rxpmbase.utils.isEmptyString
import com.google.gson.Gson
import java.lang.reflect.Type

class Storage(private val sp: SharedPreferences, private val gson: Gson) : IStorage {

    override fun <T> put(key: String, item: T) = sp.edit().putString(key, gson.toJson(item)).apply()

    override fun <T> get(key: String, type: Type): T? {
        val json = sp.getString(key, "")
        return if (isEmptyString(json)) null else gson.fromJson(json, type)
    }

    override fun putString(key: String, string: String) = sp.edit().putString(key, string).apply()

    override fun getString(key: String): String? = sp.getString(key, null)

    override fun putLong(key: String, value: Long) = sp.edit().putLong(key, value).apply()

    override fun getLong(key: String, defaultValue: Long): Long = sp.getLong(key, defaultValue)

    override fun putInt(key: String, value: Int) = sp.edit().putInt(key, value).apply()

    override fun getInt(key: String, defaultValue: Int): Int = sp.getInt(key, defaultValue)

    override fun putBoolen(key: String, value: Boolean) = sp.edit().putBoolean(key, value).apply()

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean = sp.getBoolean(key, defaultValue)

    override fun <T> putCollection(key: String, items: List<T>) = put(key, items)

    override fun <T> getCollection(key: String, type: Type): List<T>? = get(key, type)

    override fun remove(key: String) = sp.edit().remove(key).apply()

}
