package com.gallery.functional.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserDatabaseEntity)

    @Query("SELECT * FROM user_database WHERE email == :email")
    fun getByEmail(email: String): UserDatabaseEntity?

}