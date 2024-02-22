package com.gallery.functional.database.room

import android.util.Log
import com.gallery.functional.database.mapper.DatabaseMapper
import com.gallery.functional.database.model.User
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

    override suspend fun get(email: String): User? {
        return CoroutineScope(Dispatchers.IO).async {
            DatabaseMapper.map(
                database.userDao?.getByEmail(
                    email
                )
            )
        }.await()
    }
}