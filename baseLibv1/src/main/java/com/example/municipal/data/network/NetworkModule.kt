package com.example.municipal.data.network


import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrlProvider(): BaseUrlProvider = BaseUrlProviderImpl()


    @Provides
    @Singleton
    fun provideOkHttpClientProvider(): OkHttpClientProvider {
        return CustomOkHttpClientProvider()
    }

    @Singleton
    @Provides
    @RuntimeOkHttpClient
    fun provideRuntimeOkHttpClient(okHttpClientProvider: OkHttpClientProvider): OkHttpClient {
        return okHttpClientProvider.get()
    }
    @Singleton
    @Provides
    fun provideContext():ApplicationContext{
        return ApplicationContext()
    }
    @Singleton
    @Provides
    fun provideOkHttp():OkHttpClient{
        val interceptor =  HttpLoggingInterceptor();
//        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
        val okHttpClient=OkHttpClient.Builder().addInterceptor(interceptor)
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
        return okHttpClient.build()
    }


    @Singleton
    @Provides
    @RetrofitMain
    fun provideRetrofit( okHttpClient: OkHttpClient):Retrofit=
        Retrofit.Builder().baseUrl("")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    @RetrofitMunicipal
    fun provideRetrofitMunicipal(@RuntimeOkHttpClient okHttpClient: OkHttpClient,baseUrlProvider: BaseUrlProvider):Retrofit{

        return Retrofit.Builder()
            .baseUrl(baseUrlProvider.getBaseUrl())//runtime provide
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    @RetrofitMainService
    fun provideFlowerApi(@RetrofitMain retrofit: Retrofit): RetrofitService =retrofit.create(RetrofitService::class.java)

    @Singleton
    @Provides
    @RetrofitMunicipalService
    fun provideFlowerApi2(@RetrofitMunicipal retrofit: Retrofit,baseUrlProvider: BaseUrlProvider): RetrofitService =ApiServiceImpl(baseUrlProvider)


    @Singleton
    @Provides
    @RetrofitMainRepo
    fun provideMainRepo(@RetrofitMunicipalService retrofitService: RetrofitService,@RetrofitMainService retrofitService2: RetrofitService): MainRepository = MainRepository(retrofitService,retrofitService2)

    @Singleton
    @Provides
    @RetrofitMunicipalRepo
    fun provideMunicipalRepo(@RetrofitMunicipalService retrofitService: RetrofitService,@RetrofitMainService retrofitService2: RetrofitService): MainRepository = MainRepository(retrofitService,retrofitService2)

//    @Singleton
//    @Provides
//    fun provideMainRepoTest(okHttpClientProvider: OkHttpClientProvider,@RetrofitMainService retrofitService2: RetrofitService): MainRepository2 = MainRepository2(okHttpClientProvider,retrofitService2)


}