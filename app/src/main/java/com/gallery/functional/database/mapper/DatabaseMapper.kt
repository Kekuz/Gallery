package com.gallery.functional.database.mapper

import com.gallery.functional.database.model.User
import com.gallery.functional.database.room.UserDatabaseEntity

object DatabaseMapper {
    fun map(input: User): UserDatabaseEntity =
        UserDatabaseEntity(
            input.userName,
            input.birthday,
            input.phoneNumber,
            input.email,
            input.password,
        )


    fun map(input: UserDatabaseEntity?): User? =
        if (input == null) null
        else
            User(
                input.userName,
                input.birthday,
                input.phoneNumber,
                input.email,
                input.password,
            )

}