package com.gallery.functional.database.room

import com.gallery.functional.database.model.User

interface DatabaseClient {
    fun save(user: User)

    suspend fun get(email: String): User?

}