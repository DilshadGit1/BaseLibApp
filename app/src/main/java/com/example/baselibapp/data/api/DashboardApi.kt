package com.example.baselibapp.data.api

import com.example.baselibapp.data.model.DashboardResponse
import retrofit2.http.GET

interface DashboardApi {
    @GET("api/defaultdashboard/upcitizen")
    suspend fun getDashboard(): DashboardResponse
}
