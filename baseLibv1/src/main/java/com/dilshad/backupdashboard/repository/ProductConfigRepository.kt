package com.dilshad.backupdashboard.repository


import com.dilshad.backupdashboard.data.api.ProductConfigApi
import com.dilshad.libconfig.data.local.ProductConfigDao
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import javax.inject.Inject

class ProductConfigRepository @Inject constructor(
    private val api: ProductConfigApi,private val productConfigDao: ProductConfigDao
) {
    suspend fun preloadData(): JsonArray {
        return api.getProductConfig()
    }

    fun getPdDao()=productConfigDao
}