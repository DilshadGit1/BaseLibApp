package com.dilshad.libconfig.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lib_data")
data class ProductConfig(@PrimaryKey val dbId: Int, val uniqueId: String, val name: String, val loginId: String, val loginPwd: String)