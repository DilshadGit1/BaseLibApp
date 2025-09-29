package com.dilshad.libconfig.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilshad.libconfig.data.model.ProductConfig

@Database(entities = [ProductConfig::class], version = 1, exportSchema = false)
abstract class LibDatabase : RoomDatabase() {
    abstract fun productConfigDao(): ProductConfigDao
}