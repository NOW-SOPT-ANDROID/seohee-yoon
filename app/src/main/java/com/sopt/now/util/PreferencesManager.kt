package com.sopt.now.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.sopt.now.util.KeyStorage.USER_PREF

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences(USER_PREF, MODE_PRIVATE)

    fun getString(key: String, defValue: String): String = prefs.getString(key, defValue).toString()
    fun setString(key: String, value: String?) = prefs.edit().putString(key, value).apply()

    fun getInt(key: String, defValue: Int): Int = prefs.getInt(key, defValue).toInt()
    fun setInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()
}