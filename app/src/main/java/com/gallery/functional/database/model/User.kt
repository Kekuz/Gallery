package com.gallery.functional.database.model

import java.util.Date

data class User(
    val userName: String,
    val birthday: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
)