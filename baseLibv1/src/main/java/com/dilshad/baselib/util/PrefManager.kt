package com.dilshad.baselib.util


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PrefManager {

    private const val DEFAULT_PREF = "default_pref"

    @SuppressLint("DiscouragedApi")
    fun getPrefs(context: Context): SharedPreferences {
        return try {
            // Try to use main project context + app_name string
            val mainAppContext = context.createPackageContext(
                context.packageName, // parent app package
                0
            )
            val prefName = mainAppContext.getString(
                mainAppContext.resources.getIdentifier("app_name", "string", mainAppContext.packageName)
            )
            mainAppContext.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        } catch (e: Exception) {
            // Fallback in case app_name not found or error
            context.getSharedPreferences(DEFAULT_PREF, Context.MODE_PRIVATE)
        }
    }

    fun putString(context: Context, key: String, value: String) {
        getPrefs(context).edit() { putString(key, value) }
    }

    fun getString(context: Context, key: String, defValue: String? = null): String? {
        return getPrefs(context).getString(key, defValue)
    }

    fun putBoolean(context: Context, key: String, value: Boolean) {
        getPrefs(context).edit() { putBoolean(key, value) }
    }

    fun getBoolean(context: Context, key: String, defValue: Boolean = false): Boolean {
        return getPrefs(context).getBoolean(key, defValue)
    }

    fun putInt(context: Context, key: String, value: Int) {
        getPrefs(context).edit() { putInt(key, value) }
    }

    fun getInt(context: Context, key: String, defValue: Int = 0): Int {
        return getPrefs(context).getInt(key, defValue)
    }

    fun clear(context: Context) {
        getPrefs(context).edit() { clear() }
    }
}
