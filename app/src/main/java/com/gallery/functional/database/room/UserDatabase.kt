package com.gallery.functional.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gallery.functional.database.room.UserDao
import com.gallery.functional.database.room.UserDatabaseEntity

@Database(
    entities = [UserDatabaseEntity::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao?
}