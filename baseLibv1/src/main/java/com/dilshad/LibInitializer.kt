package com.dilshad


import android.content.Context
import android.util.Log
import com.dilshad.apifallback.ApiFallbackHelper
import com.dilshad.apifallback.utils.LibConstants
import com.dilshad.appInfoSdk.AppInfoSDK
import com.dilshad.backupdashboard.SplashActivity
import com.dilshad.libconfig.data.model.ProductConfig
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object LibInitializer {

    private var cachedData: JsonObject? = null

    fun init(appContext: Context,appId: String=appContext.packageName,baseUrl: String) {
        // Get Repository via Hilt manually (since object is not @AndroidEntryPoint)
        val entryPoint = EntryPointAccessors.fromApplication(
            appContext,
            LibEntryPoint::class.java
        )
        val repo = entryPoint.libRepository()
        val dao = repo.getPdDao()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val jsonArray = repo.preloadData()
                val pd=findProductConfigById(jsonArray,appId)
                if(pd!=null){
                    dao.insertData(pd)
                }
                apiFallBackHandle(appContext,baseUrl)
                Log.d("LibInitializer", "✅ Preloaded: $pd")

                //info sdk init
                AppInfoSDK.init(appContext)
            } catch (e: Exception) {
                Log.e("LibInitializer", "❌ Error: ${e.message}")
            }
        }
    }

    private fun findProductConfigById(jsonArray: JsonArray, appId: String): ProductConfig? {
        val gson = Gson()

        for (i in 0 until jsonArray.size()) {
            val jsonObject = jsonArray.get(i).asJsonObject
            val uniqueId = jsonObject.get("uniqueId").asString
            if (uniqueId == appId) {
                return gson.fromJson(jsonObject.toString(), ProductConfig::class.java)
            }
        }
        return null
    }
    private fun apiFallBackHandle(context: Context,baseUrl: String){
        ApiFallbackHelper.checkAndRedirect(context = context,baseUrl, SplashActivity::class.java){
            LibConstants.apiWorkingStatus=it
        }
    }
}
