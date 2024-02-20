package com.gallery.database

import com.gallery.database.model.User
import com.gallery.database.room.DatabaseClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatabaseRepository(private val databaseClient: DatabaseClient) {
    fun saveUser(user: User, exception: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                databaseClient.save(user)
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    exception.invoke(e.message)
                }

            }

        }
    }

    fun getUser(userName: String, consumer: (User?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            consumer.invoke(databaseClient.get(userName))
        }
    }

}