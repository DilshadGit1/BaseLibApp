package com.dilshad.backupdashboard.repository

import com.dilshad.backupdashboard.data.api.WeatherApi
import com.dilshad.backupdashboard.di.Dl_lib_Apis
import javax.inject.Inject

class WeatherRepository @Inject constructor(
   @Dl_lib_Apis private val api: WeatherApi
) {
    suspend fun getWeather(lat: Double, lon: Double) = api.getWeather(lat, lon)
}
