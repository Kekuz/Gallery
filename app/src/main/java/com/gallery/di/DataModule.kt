package com.gallery.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.gallery.functional.database.DatabaseRepository
import com.gallery.functional.database.room.DatabaseClient
import com.gallery.functional.database.room.RoomDatabaseClient
import com.gallery.functional.database.room.UserDatabase
import com.gallery.functional.shared_prefs.SharedPrefsAuthSaveStorage
import com.gallery.functional.validate.ValidateLoginInInteractor
import com.gallery.functional.validate.ValidateRegisterInteractor
import com.google.gson.Gson
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
    fun provideAuthSharedPrefs(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            HISTORY_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun provideAuthGson(@ApplicationContext context: Context): Gson =
        Gson()

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

    @Provides
    @Singleton
    fun provideValidateRegisterInteractor(
        databaseRepository: DatabaseRepository,
        @ApplicationContext context: Context
    ): ValidateRegisterInteractor =
        ValidateRegisterInteractor(databaseRepository, context)

    @Provides
    @Singleton
    fun provideValidateLoginInIteractor(
        databaseRepository: DatabaseRepository,
        @ApplicationContext context: Context
    ): ValidateLoginInInteractor =
        ValidateLoginInInteractor(databaseRepository, context)

    @Provides
    @Singleton
    fun provideSharedPrefsAuthSaveStorage(
        sharedPreferences: SharedPreferences,
        gson: Gson,
    ): SharedPrefsAuthSaveStorage =
        SharedPrefsAuthSaveStorage(sharedPreferences, gson)


    private companion object {
        const val HISTORY_SHARED_PREFERENCES = "history_shared_preferences"
    }
}