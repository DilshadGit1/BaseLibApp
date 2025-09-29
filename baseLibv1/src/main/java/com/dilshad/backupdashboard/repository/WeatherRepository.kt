package com.dilshad.backupdashboard.repository

import com.dilshad.backupdashboard.data.api.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) {
    suspend fun getWeather(lat: Double, lon: Double) = api.getWeather(lat, lon)
}
