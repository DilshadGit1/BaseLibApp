package com.dilshad.apifallback


import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object ApiFallbackHelper {

    private val client = OkHttpClient()

    /**
     * Check main API status.
     * If fail -> launch LibraryActivity (your lib's screen).
     *
     * @param context Context to start activity
     * @param mainBaseUrl The base URL of the main app API
     * @param libActivityClass The Activity class from your library to launch if main API fails
     */
    fun checkAndRedirect(
        context: Context,
        mainBaseUrl: String,
        libActivityClass: Class<*>,
        doRedirect: Boolean=false,
        callback:(apiStatus: Int)->Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
             checkApi(mainBaseUrl){
                if (!it) {
                    Log.d("ApiFallbackHelper", "Main API failed → Switching to library UI")

                    if(doRedirect) {
                        val intent = Intent(context, libActivityClass).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        context.startActivity(intent)
                    }
                    callback(0)
                } else {
                    callback(1)
                    Log.d("ApiFallbackHelper", "Main API is working fine ✅")
                }
            }


        }
    }
    private fun checkApi(baseUrl: String,callback: (Boolean) -> Unit) {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(baseUrl)
                .build()

            val response: Response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val body = response.body?.string()
                Log.d("API_CHECK", "✅ Success: $body")
                callback(true)
            } else {
                Log.e("API_CHECK", "❌ Failed: ${response.code}")
                callback(false)
            }
        } catch (e: Exception) {
            Log.e("API_CHECK", "⚠️ Error: ${e.message}")
            callback(false)
        }
    }

    /**
     * Ping the API root URL (or health endpoint if available)
     */
//    private fun checkApi(baseUrl: String, callback: (Boolean) -> Unit) {
//        checkApi(baseUrl) {
//            callback(it)
//        }
//
////        val request = Request.Builder()
////            .url(baseUrl)
////            .build()
////
////        fun checkUrlReachable(url: String): Boolean {
////            return try {
////                val connection = URL(url).openConnection() as HttpURLConnection
////                connection.requestMethod = "HEAD"
////                connection.connectTimeout = 2000
////                connection.readTimeout = 2000
////                connection.responseCode == HttpURLConnection.HTTP_OK
////            } catch (e: Exception) {
////                false
////            }
////        }
////
////        client.newCall(request).enqueue(object : Callback {
////            override fun onFailure(call: Call, e: IOException) {
////                Log.d("LibAPIStatus",""+e.message)
////                callback(false)
////            }
////
////            override fun onResponse(call: Call, response: Response) {
////                callback(response.isSuccessful)
////                Log.d("LibAPIStatus",""+response.isSuccessful)
////                response.close()
////            }
////        })
//    }
}
