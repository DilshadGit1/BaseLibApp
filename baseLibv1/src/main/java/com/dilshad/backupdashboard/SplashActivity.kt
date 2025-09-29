package com.dilshad.backupdashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dilshad.android.baselib.databinding.ActivitySplashBinding
import com.dilshad.backupdashboard.login.LoginActivity
import com.dilshad.backupdashboard.utils.PreferenceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// This activity shows the splash screen with a Lottie animation.
class SplashActivity : AppCompatActivity() {

    // View binding instance for the splash screen layout.
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PreferenceManager.initialize(this@SplashActivity)

        // Use a coroutine to handle the splash screen duration.
        lifecycleScope.launch {
            // Wait for 3 seconds. Adjust this duration as needed.
            delay(3000)

            // Create an Intent to navigate to the LoginActivity.
            if(!PreferenceManager.isLoggedIn()) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }

            // Finish the splash activity so the user cannot navigate back to it.
            finish()
        }
    }
}
