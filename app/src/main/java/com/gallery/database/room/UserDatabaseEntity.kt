package com.gallery.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("user_database")
data class UserDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val birthday: Date,
    val phoneNumber: String,
    val email: String,
    val password: String,
)