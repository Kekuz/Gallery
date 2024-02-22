package com.gallery.functional.shared_prefs

import android.content.SharedPreferences
import com.gallery.functional.database.model.User
import com.google.gson.Gson

class SharedPrefsAuthSaveStorage(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {

    fun getUser(): User? {
        val json = sharedPreferences.getString(USER_KEY, "") ?: ""

        return if (json.isEmpty()) {
            null
        } else {
            gson.fromJson(json, User::class.java)
        }

    }

    fun saveUser(user: User) {
        val json = gson.toJson(user)

        sharedPreferences.edit()
            .putString(USER_KEY, json)
            .apply()
    }

    fun clear() {
        sharedPreferences.edit()
            .putString(USER_KEY, "")
            .apply()
    }

    private companion object {
        const val USER_KEY = "user_key"
    }

}