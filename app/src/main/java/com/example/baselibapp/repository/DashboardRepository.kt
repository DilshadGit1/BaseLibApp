package com.example.baselibapp.repository

import com.example.baselibapp.data.api.DashboardApi
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: DashboardApi
) {
    suspend fun getDashboard() = api.getDashboard()
}
