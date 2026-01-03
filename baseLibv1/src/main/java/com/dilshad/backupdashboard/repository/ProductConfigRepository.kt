package com.dilshad.backupdashboard.repository


import com.dilshad.backupdashboard.data.api.ProductConfigApi
import com.dilshad.backupdashboard.di.Dl_lib_Apis
import com.dilshad.libconfig.data.local.ProductConfigDao
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import javax.inject.Inject
import javax.inject.Named

class ProductConfigRepository @Inject constructor(
    @Dl_lib_Apis private val api: ProductConfigApi, private val productConfigDao: ProductConfigDao
) {
    suspend fun preloadData(): JsonArray {
        return api.getProductConfig()
    }

    fun getPdDao()=productConfigDao
}