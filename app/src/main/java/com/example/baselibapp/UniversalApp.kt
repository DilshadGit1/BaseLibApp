package com.example.baselibapp

import android.app.Application
import com.dilshad.LibInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UniversalApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LibInitializer.init(this, baseUrl = "https://stage.logikoof.orc/api/")
    }
}