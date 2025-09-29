package com.dilshad.apifallback

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.dilshad.apifallback.ui.DefaultLauncherActivity
import com.dilshad.apifallback.utils.LibConstants
import com.dilshad.backupdashboard.SplashActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

object LibLauncher {

    fun startLauncher(activity: Activity){
        if(!LibConstants.isParentProjectAPIChecked) {
            val intent = Intent(activity, DefaultLauncherActivity::class.java)
            intent.putExtra("className", activity::class.java.name)
            intent.putExtra("packageName", activity.packageName)
            intent.putExtra("caller_class", activity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
    private fun findClassName(activity: Activity): Intent?{

        try {
            val className = activity::class.java.name
            val intent = Intent()
            activity.localClassName
            intent.setClassName(
                activity.packageName,
                className // <-- your main app activity fqcn
            )
            return intent
        } catch (e: Exception) {
            Log.e("AppEntryActivity", "MainActivity not found: $e")
        }
        return null
    }

}