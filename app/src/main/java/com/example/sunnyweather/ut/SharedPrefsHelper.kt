package com.example.sunnyweather.ut

import android.content.Context
import android.content.SharedPreferences
import android.util.JsonToken

class SharedPrefsHelper(context: Context) {
    // 共享参数文件名
    private val PREFS_NAME = "app_prefs"

    // 键名常量
    private val KEY_TOKEN = "user_token"
    private val KEY_IS_FIRST_LAUNCH = "is_first_launch"

    // 初始化 SharedPreferences
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * 保存用户Token
     * @param token 要保存的Token字符串
     */
    fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    /**
     * 获取用户Token
     * @return 保存的Token，无则返回空字符串
     */
    fun getToken(): String {
        return prefs.getString(KEY_TOKEN, "") ?: ""
    }

    /**
     * 标记App是否首次启动
     * @param isFirstLaunch true=首次，false=非首次
     */
    fun setFirstLaunch(isFirstLaunch: Boolean) {
        prefs.edit().putBoolean(KEY_IS_FIRST_LAUNCH, isFirstLaunch).apply()
    }

    /**
     * 判断App是否首次启动
     * @return true=首次，false=非首次
     */
    fun isFirstLaunch(): Boolean {
        return prefs.getBoolean(KEY_IS_FIRST_LAUNCH, true)
    }
}