package com.dilshad.libconfig.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilshad.libconfig.data.model.ProductConfig

@Dao
interface ProductConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(entity: ProductConfig)

    @Query("SELECT * FROM lib_data LIMIT 1")
    suspend fun getData(): ProductConfig?
}
