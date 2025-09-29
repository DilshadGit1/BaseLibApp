package com.dilshad.backupdashboard.data.api

import com.dilshad.backupdashboard.data.model.DashboardResponse
import retrofit2.http.GET

interface DashboardApi {
    @GET("api/defaultdashboard/upcitizen")
    suspend fun getDashboard(): DashboardResponse
}
