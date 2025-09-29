package com.dilshad.backupdashboard.utils

import android.content.Context
import android.content.SharedPreferences

// A singleton class to manage SharedPreferences for the application.
// This centralizes all logic for saving and retrieving key-value pairs.
object PreferenceManager {

    private const val PREF_NAME = "login_pref"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private lateinit var sharedPreferences: SharedPreferences

    // This method must be called once in the Application class or the first activity.
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Saves the login status to SharedPreferences.
    fun saveLoginStatus(isLoggedIn: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            apply()
        }
    }

    // Checks if the user is currently logged in.
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    // Clears all data from SharedPreferences.
    fun clearPreferences() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}
