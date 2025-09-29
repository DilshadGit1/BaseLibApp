package com.dilshad.libconfig.di

import com.dilshad.libconfig.data.local.LibDatabase
import com.dilshad.libconfig.data.local.ProductConfigDao


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LibDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LibDatabase {
        return Room.databaseBuilder(
            context,
            LibDatabase::class.java,
            "lib_database"
        ).build()
    }

    @Provides
    fun provideDao(db: LibDatabase): ProductConfigDao = db.productConfigDao()
}
