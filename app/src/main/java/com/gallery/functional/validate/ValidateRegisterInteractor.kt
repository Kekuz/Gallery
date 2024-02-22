package com.gallery.functional.validate

import android.content.Context
import android.util.Log
import com.gallery.R
import com.gallery.functional.database.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ValidateRegisterInteractor(
    private val databaseRepository: DatabaseRepository,
    private val context: Context,
) {
    fun validateUserName(username: String): String? {
        return if (username.length >= 3) {
            null
        } else {
            context.getString(R.string.short_name)
        }

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

    suspend fun validateEmail(email: String): String? {

        var alreadyRegistered: Boolean
        withContext(Dispatchers.IO) {
            alreadyRegistered = databaseRepository.getUser(email) != null
        }
        Log.d("alreadyRegistered", alreadyRegistered.toString())

        if(alreadyRegistered){
            return context.getString(R.string.email_already_taken)
        }

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