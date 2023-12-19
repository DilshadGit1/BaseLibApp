package com.example.municipal.data.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseUrlProviderImpl @Inject constructor() : BaseUrlProvider {
    private var baseUrl = "https://example.com"

    override fun setBaseUrl(string: String) {
        baseUrl = string
    }

    override fun getBaseUrl(): String {
        return baseUrl
    }
}