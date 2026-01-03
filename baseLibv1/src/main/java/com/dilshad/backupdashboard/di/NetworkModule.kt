package com.dilshad.backupdashboard.di

import com.dilshad.backupdashboard.data.api.DashboardApi
import com.dilshad.backupdashboard.data.api.ProductConfigApi
import com.dilshad.backupdashboard.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("dl_lib_HttpLogInterceptor")
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Named("dl_lib_OkHttpClient")
    fun provideOkHttp(@Named("dl_lib_HttpLogInterceptor") logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(logging).build()

    @Provides
    @Named("dl_lib_weather")
    fun provideWeatherRetrofit(@Named("dl_lib_OkHttpClient")client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Named("dl_lib_dashboard")
    fun provideDashboardRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://demo4245897.mockable.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Dl_lib_Apis
    fun provideWeatherApi(@Named("dl_lib_weather") retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Provides
    @Dl_lib_Apis
    fun provideDashboardApi(@Named("dl_lib_dashboard") retrofit: Retrofit): DashboardApi =
        retrofit.create(DashboardApi::class.java)

    @Provides
    @Dl_lib_Apis
    fun providePCApi(@Named("dl_lib_dashboard") retrofit: Retrofit): ProductConfigApi =
        retrofit.create(ProductConfigApi::class.java)
}
