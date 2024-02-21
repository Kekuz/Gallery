package com.gallery.database

import com.gallery.database.model.User
import com.gallery.database.room.DatabaseClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatabaseRepository(private val databaseClient: DatabaseClient) {

    fun saveUser(user: User) {
        databaseClient.save(user)
    }

    suspend fun getUser(email: String): User? {
        return databaseClient.get(email)

    }

}