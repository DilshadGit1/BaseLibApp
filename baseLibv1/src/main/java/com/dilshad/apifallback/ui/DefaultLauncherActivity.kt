package com.dilshad.apifallback.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dilshad.apifallback.utils.LibConstants
import com.dilshad.backupdashboard.SplashActivity
import kotlinx.coroutines.*

class DefaultLauncherActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Simple full-screen loader
        setContentView(FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            progressBar = ProgressBar(this@DefaultLauncherActivity).apply {
                isIndeterminate = true
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                )

            }
            val liner= LinearLayout(this@DefaultLauncherActivity).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )

                orientation= LinearLayout.VERTICAL
            }

            val tv= TextView(this@DefaultLauncherActivity).apply {
                text="Loading please wait..."
                textSize=14f
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
                gravity= Gravity.CENTER_HORIZONTAL
            }
            tv.setTextColor(Color.BLACK)
            liner.addView(progressBar)
            liner.addView(tv)
            addView(liner)
        })

        handleLaunch()
    }

    private fun handleLaunch() {
        job = CoroutineScope(Dispatchers.IO).launch {
            while (LibConstants.apiWorkingStatus == -1 && isActive) {
                // wait until api status is updated
                delay(1000)
            }
            withContext(Dispatchers.Main) {
                checkApiStatus()
            }
        }
    }

    private fun checkApiStatus() {
        when (LibConstants.apiWorkingStatus) {
            0 -> {
                // Go to lib Splash screen
                startActivity(Intent(this, SplashActivity::class.java))
                LibConstants.isParentProjectAPIChecked=true
                finish()
            }
            1 -> {
                // Open parent project main activity
                openMainProjectActivity()
                LibConstants.isParentProjectAPIChecked=true
                finish()
            }
            else -> {
                Log.e("DefaultLauncherActivity", "⚠️ Unknown API status: ${LibConstants.apiWorkingStatus}")
            }
        }
    }

    private fun openMainProjectActivity() {
        try {
            val className = intent.getStringExtra("className")
            val pkg = intent.getStringExtra("packageName")

            if (!className.isNullOrEmpty() && !pkg.isNullOrEmpty()) {
                val intent = Intent().apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    setClassName(pkg, className)
                }
                startActivity(intent)
            } else {
                Log.e("AppEntryActivity", "className or packageName not provided")
            }
        } catch (e: Exception) {
            Log.e("AppEntryActivity", "MainActivity not found: $e")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
