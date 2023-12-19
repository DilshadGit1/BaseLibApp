package com.example.municipal.data.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMain

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMunicipal


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMainService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMunicipalService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMainRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMunicipalRepo

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RuntimeOkHttpClient