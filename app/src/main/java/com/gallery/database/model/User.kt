package com.gallery.database.model

import java.util.Date

data class User(
    val userName: String,
    val birthday: String,//TODO Заменить на Date
    val phoneNumber: String,
    val email: String,
    val password: String,
)