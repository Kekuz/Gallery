package com.gallery.validate

import android.content.Context
import android.util.Log
import com.gallery.R
import com.gallery.database.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ValidateInteractor(
    private val databaseRepository: DatabaseRepository,
    private val context: Context,
) {
    suspend fun validateUserName(username: String): String? {
        var db: Boolean
        withContext(Dispatchers.IO) {
            db = databaseRepository.getUser(username) == null
        }
        Log.d("validate user name", db.toString())
        return if (db) null else context.getString(R.string.name_already_taken)
    }

    fun validateBirthday(birthday: String): String? {
        if (birthday == "") {
            return null
        }
        val birthdayDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            .parse(birthday)!!
        val today = Date()
        Log.e("date", "$today $birthdayDate")

        return if (birthdayDate.time > today.time) {
            Log.e("date", "false")
            context.getString(R.string.date_before_today)
        } else {
            Log.e("date", "true")
            null
        }
    }

    fun validatePhoneNumber(phoneNumber: String): String? {
        val countryCodeRegex =
            Regex("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")

        return if (countryCodeRegex.containsMatchIn(phoneNumber)) {
            null
        } else {
            context.getString(R.string.enter_correct_number)
        }
    }

    fun validateEmail(email: String): String? {
        val emailRegex =
            Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")

        return if (emailRegex.containsMatchIn(email)) {
            null
        } else {
            context.getString(R.string.enter_correct_email)
        }
    }

    fun validatePassword(password: String): String? {
        return if (password.length <= 8) {
            context.getString(R.string.password_less_then_8)
        } else {
            null
        }
    }

    fun validateConfirmPassword(firstPassword: String, secondPassword: String): String? {
        return if (firstPassword == secondPassword) {
            null
        } else {
            context.getString(R.string.passwords_dont_match)
        }
    }
}