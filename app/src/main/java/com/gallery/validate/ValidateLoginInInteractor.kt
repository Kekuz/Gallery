package com.gallery.validate

import android.content.Context
import android.util.Log
import com.gallery.R
import com.gallery.database.DatabaseRepository
import com.gallery.database.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ValidateLoginInInteractor(
    private val databaseRepository: DatabaseRepository,
    private val context: Context,
) {
    suspend fun validateLogin(email: String): String? {
        var user: User?
        withContext(Dispatchers.IO) {
            user = databaseRepository.getUser(email)
        }
        Log.d("user", user.toString())


        return if (user == null) {
            context.getString(R.string.user_not_found)
        } else {
            null
        }
    }

    suspend fun validatePassword(email: String, password: String): String? {
        var user: User?
        withContext(Dispatchers.IO) {
            user = databaseRepository.getUser(email)
        }
        Log.d("user", user.toString())


        return if (user == null) {
            context.getString(R.string.invalid_password)
        } else {
            if (user?.password != password) {
                context.getString(R.string.invalid_password)
            } else {
                null
            }
        }
    }
}