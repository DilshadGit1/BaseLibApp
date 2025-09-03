package com.example.baselibapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baselibapp.data.model.InfoItem
import com.example.baselibapp.repository.DashboardRepository
import com.example.baselibapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepo: WeatherRepository,
    private val dashboardRepo: DashboardRepository
) : ViewModel() {

    private val _weather = MutableLiveData<String>()
    val weather: LiveData<String> = _weather

    private val _middleCard = MutableLiveData<List<InfoItem>>()
    val middleCard: LiveData<List<InfoItem>> = _middleCard

    private val _bottomCard = MutableLiveData<List<InfoItem>>()
    val bottomCard: LiveData<List<InfoItem>> = _bottomCard

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = weatherRepo.getWeather(lat, lon)
                val current = response.current_weather
                _weather.postValue("${current.temperature}°C, Wind ${current.windspeed} km/h")
            } catch (e: Exception) {
                _weather.postValue("Failed to load")
            }
        }
    }

    fun fetchDashboard() {
        viewModelScope.launch {
            try {
                val response = dashboardRepo.getDashboard()
                _middleCard.postValue(response.middleCard)
                _bottomCard.postValue(response.thirdCard)
            } catch (e: Exception) {
                _middleCard.postValue(emptyList())
                _bottomCard.postValue(emptyList())
            }
        }
    }
}

