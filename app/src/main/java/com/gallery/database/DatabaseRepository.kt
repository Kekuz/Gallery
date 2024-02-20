package com.gallery.database

import com.gallery.database.model.User
import com.gallery.database.room.DatabaseClient

class DatabaseRepository(private val databaseClient: DatabaseClient) {
    fun saveUser(user: User) {
        databaseClient.save(user)
    }

    fun getUser(userName: String): User? {
        return databaseClient.get(userName)
    }

}