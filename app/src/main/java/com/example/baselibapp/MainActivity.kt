package com.example.baselibapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dilshad.android.baselibapp.R
import com.dilshad.android.baselibapp.databinding.ActivityMainBinding
import com.example.baselibapp.ui.adapter.InfoAdapter
import com.example.baselibapp.ui.main.MainViewModel
import com.example.municipal.base.BaseActivity
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
}
