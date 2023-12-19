package com.example.municipal.data.network

interface BaseUrlProvider {
    fun getBaseUrl(): String
    fun setBaseUrl(string: String)
}