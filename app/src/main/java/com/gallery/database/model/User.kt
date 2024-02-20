package com.gallery.database.model

import java.util.Date

data class User(
    val id: Int,
    val userName: String,
    val birthday: Date,
    val phoneNumber: String,
    val email: String,
    val password: String,
)