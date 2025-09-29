package com.dilshad.backupdashboard.data.api

import com.dilshad.baselib.model.LoginRequest
import com.dilshad.baselib.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login") // Replace with your actual login endpoint path
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}