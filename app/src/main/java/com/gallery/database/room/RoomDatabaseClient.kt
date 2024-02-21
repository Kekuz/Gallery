package com.gallery.database.room

import android.util.Log
import com.gallery.database.mapper.DatabaseMapper
import com.gallery.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RoomDatabaseClient(private val database: UserDatabase) : DatabaseClient {


    override fun save(user: User) {
        Log.d("user saved in database", user.toString())
        CoroutineScope(Dispatchers.IO).launch {
            database.userDao?.insert(DatabaseMapper.map(user))
        }
    }

    override suspend fun get(userName: String): User? {
        return CoroutineScope(Dispatchers.IO).async {
            DatabaseMapper.map(
                database.userDao?.getByUserName(
                    userName
                )
            )
        }.await()
    }
}