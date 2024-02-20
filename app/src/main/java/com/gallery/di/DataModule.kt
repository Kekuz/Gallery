package com.gallery.di

import android.content.Context
import androidx.room.Room
import com.gallery.database.DatabaseRepository
import com.gallery.database.room.DatabaseClient
import com.gallery.database.room.RoomDatabaseClient
import com.gallery.database.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java, "user-database"
        ).build()

    @Provides
    @Singleton
    fun provideDatabaseClient(
        characterDatabase: UserDatabase,
    ): DatabaseClient =
        RoomDatabaseClient(characterDatabase)

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        databaseClient: DatabaseClient,
    ): DatabaseRepository =
        DatabaseRepository(databaseClient)
}