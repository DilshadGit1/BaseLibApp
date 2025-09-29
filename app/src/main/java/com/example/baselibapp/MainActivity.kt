package com.example.baselibapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dilshad.android.libmain.R
import com.dilshad.apifallback.LibLauncher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        LibLauncher.startLauncher(this)
//        startActivity(Intent(this@MainActivity,com.dilshad.backupdashboard.SplashActivity::class.java))
    }
}