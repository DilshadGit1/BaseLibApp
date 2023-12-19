package com.example.municipal.data.network

import okhttp3.OkHttpClient

interface OkHttpClientProvider {
    fun get(): OkHttpClient
}