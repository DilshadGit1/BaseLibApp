package com.dilshad.backupdashboard.data.api

import com.dilshad.backupdashboard.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast?current_weather=true")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double
    ): WeatherResponse
}
