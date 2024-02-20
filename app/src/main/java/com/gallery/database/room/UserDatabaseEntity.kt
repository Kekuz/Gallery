package com.gallery.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("user_database")
data class UserDatabaseEntity(
    @PrimaryKey
    val userName: String,
    val birthday: String,//TODO заменить на Date
    val phoneNumber: String,
    val email: String,
    val password: String,
)