package com.dilshad.backupdashboard.data.api

import com.dilshad.baselib.model.LoginRequest
import com.dilshad.baselib.model.LoginResponse
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductConfigApi {

    @GET("productconfig") // Replace with your actual login endpoint path
    suspend fun getProductConfig(): JsonArray
}