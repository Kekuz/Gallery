package com.gallery.database.room

import com.gallery.database.model.User

interface DatabaseClient {
    fun save(user: User)

    suspend fun get(email: String): User?

}