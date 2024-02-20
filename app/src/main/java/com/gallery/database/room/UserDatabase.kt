package com.gallery.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gallery.database.room.UserDao
import com.gallery.database.room.UserDatabaseEntity

@Database(
    entities = [UserDatabaseEntity::class],
    version = 1
)
@TypeConverters(DateTypeConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao?
}