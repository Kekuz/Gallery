package com.gallery.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("user_database")
data class UserDatabaseEntity(
    val userName: String,
    val birthday: String,
    val phoneNumber: String,
    @PrimaryKey
    val email: String,
    val password: String,
)