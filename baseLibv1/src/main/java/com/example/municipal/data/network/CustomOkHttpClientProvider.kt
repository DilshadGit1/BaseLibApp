package com.example.municipal.data.network

import android.content.Context
import com.example.municipal.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
class CustomOkHttpClientProvider @Inject constructor() :OkHttpClientProvider  {

     private var myokHttpClient: OkHttpClient?=null
    val userAgent = "Your User-Agent String"


    init {
        setOkHttpClientWithNoCache()
    }
    fun setOkHttpClient(client: OkHttpClient) {
        myokHttpClient = client
    }
    fun setOkHttpClientWithNoCache() {
        val interceptor =  HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        val okHttpClient=OkHttpClient.Builder().addInterceptor(interceptor)
//        okHttpClient.addInterceptor(UserAgentInterceptor(userAgent))
        okHttpClient.connectTimeout(60,TimeUnit.SECONDS)
        okHttpClient.readTimeout(60,TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60,TimeUnit.SECONDS)
        this.myokHttpClient=okHttpClient.build()
    }
    fun setOkHttpClientWithCache(context: Context) {
        val interceptor =  HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val cacheDirectory = File(context.cacheDir, "http-cache")
        val cache = Cache(cacheDirectory, cacheSize.toLong())

        val maxAge = 12 * 60 * 60 // 12 hours
        val httpClient = OkHttpClient.Builder().cache(cache)
            .addInterceptor(interceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
         .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge") // Cache response for 60 seconds
                    .build()
                chain.proceed(request)
            }
//            .addInterceptor(UserAgentInterceptor(userAgent))
            .build()
        myokHttpClient = httpClient
    }

    override fun get(): OkHttpClient {
        if (myokHttpClient == null) {
            // Return a default OkHttpClient or throw an exception if not set
            throw IllegalStateException("OkHttpClient not set.")
        }
        return myokHttpClient!!
    }


}