package com.dilshad.baselib.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object MyLibraryModule {

    @Named("dllibretrofit")
    @Provides
    fun provideRetrofit(myLibraryConfig: MyLibraryConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(myLibraryConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMyLibraryConfig(): MyLibraryConfig {
        // You can provide the configuration dynamically or allow users to set it
        return MyLibraryConfigImpl("https://default-base-url.com")
    }
}