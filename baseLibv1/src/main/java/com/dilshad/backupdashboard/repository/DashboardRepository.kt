package com.dilshad.backupdashboard.repository

import com.dilshad.backupdashboard.data.api.DashboardApi
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: DashboardApi
) {
    suspend fun getDashboard() = api.getDashboard()
}
