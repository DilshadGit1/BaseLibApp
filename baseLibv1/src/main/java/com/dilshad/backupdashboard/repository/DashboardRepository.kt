package com.dilshad.backupdashboard.repository

import com.dilshad.backupdashboard.data.api.DashboardApi
import com.dilshad.backupdashboard.di.Dl_lib_Apis
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    @Dl_lib_Apis private val api: DashboardApi
) {
    suspend fun getDashboard() = api.getDashboard()
}
