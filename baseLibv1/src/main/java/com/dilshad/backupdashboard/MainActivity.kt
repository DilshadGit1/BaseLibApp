package com.dilshad.backupdashboard

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dilshad.android.baselib.R
import com.dilshad.android.baselib.databinding.ActivityMainBinding
import com.dilshad.backupdashboard.login.LoginActivity
import com.dilshad.backupdashboard.ui.adapter.InfoAdapter
import com.dilshad.backupdashboard.ui.main.MainViewModel
import com.dilshad.backupdashboard.utils.PreferenceManager
import com.dilshad.baselib.base.BaseActivity

import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {


    private val viewModel: MainViewModel by viewModels()


    override fun onInitialized() {
        // Date & Time
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val now = Date()
        binding.tvDate.text = dateFormat.format(now)
        binding.tvTime.text = timeFormat.format(now)

        // Observe weather
        viewModel.weather.observe(this) { binding.tvWeather.text = it }
        viewModel.fetchWeather(28.61, 77.23) // Delhi coords

        // Observe dashboard data
        viewModel.middleCard.observe(this) {
            binding.recyclerMiddle.layoutManager = GridLayoutManager(this, 3)
            binding.recyclerMiddle.adapter = InfoAdapter(it)
        }
        viewModel.bottomCard.observe(this) {
            binding.recyclerBottom.layoutManager = GridLayoutManager(this, 3)
            binding.recyclerBottom.adapter = InfoAdapter(it)
        }

        // Fetch dashboard API
        viewModel.fetchDashboard()
    }

    override fun setUpClicks() {

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                // 👉 Handle button click here
//                Toast.makeText(this, "Refresh clicked!", Toast.LENGTH_SHORT).show()

                // Example: call your refresh function
                logout()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        // Your refresh logic goes here
        PreferenceManager.clearPreferences()
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }


}