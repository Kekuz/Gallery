package com.gallery.database.room

import android.util.Log
import com.gallery.database.mapper.DatabaseMapper
import com.gallery.database.model.User

class RoomDatabaseClient(private val database: UserDatabase) : DatabaseClient {


    override fun save(user: User) {
        Log.d("user saved in database", user.toString())
        database.userDao?.insert(DatabaseMapper.map(user))
    }

    override fun get(userName: String): User? {
        return DatabaseMapper.map(database.userDao?.getByUserName(userName))
    }
}