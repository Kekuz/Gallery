package com.gallery.database.mapper

import com.gallery.database.model.User
import com.gallery.database.room.UserDatabaseEntity

object DatabaseMapper {
    fun map(input: User): UserDatabaseEntity {
        return UserDatabaseEntity(
            input.id,
            input.userName,
            input.birthday,
            input.phoneNumber,
            input.email,
            input.password,
        )
    }

    fun map(input: UserDatabaseEntity?): User? =
        if (input == null) null
        else
            User(
                input.id,
                input.userName,
                input.birthday,
                input.phoneNumber,
                input.email,
                input.password,
            )

    /*fun mapToDB(input: List<Character>): List<User> {
        return input.map {
            User(
                it.id,
                it.name,
                it.status,
                it.species,
                it.type,
                it.gender,
                it.image,
                it.created,
            )
        }
    }

    fun mapFromDB(input: List<User>): List<Character> {
        return input.map {
            Character(
                it.id,
                it.name,
                it.status,
                it.species,
                it.type,
                it.gender,
                it.image,
                it.created,
            )
        }
    }*/
}