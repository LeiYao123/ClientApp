package com.quick.app.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PreferencesManager {

    private const val PREFS_NAME = "xiaomi_cloud_mall"

    lateinit var sharedPreferences: SharedPreferences
        private set  // 外部可以读取，但不能修改
    val gson = Gson() // Gson 实例

    // 初始化 SharedPreferences，需在 Application 入口调用
    fun init(context: Context) {
        sharedPreferences =
            context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // 存储普通数据
    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    // 存储 JSON 对象
    fun <T> putObject(key: String, obj: T) {
        val json = gson.toJson(obj) // 序列化对象
        sharedPreferences.edit().putString(key, json).apply()
    }

    // 读取 JSON 对象
    inline fun <reified T> getObject(key: String, defaultValue: T? = null): T? {
        val json = sharedPreferences.getString(key, null) ?: return defaultValue
        return try {
            gson.fromJson(json, T::class.java) // 反序列化对象
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    // 存储 JSON 数组
    fun <T> putList(key: String, list: List<T>) {
        val json = gson.toJson(list) // 序列化列表
        sharedPreferences.edit().putString(key, json).apply()
    }

    // 读取 JSON 数组
    inline fun <reified T> getList(key: String): List<T> {
        val json = sharedPreferences.getString(key, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<T>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    // 删除某个键
    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    // 清空所有数据
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}

/**
 * 使用示例
 *
 * val user = User("JetpackUser", 25)
 * SharedPreferencesHelper.putObject("user", user) // 存储 JSON
 * val storedUser: User? = SharedPreferencesHelper.getObject("user") // 读取 JSON
 * println(storedUser?.name) // 输出: JetpackUser
 */