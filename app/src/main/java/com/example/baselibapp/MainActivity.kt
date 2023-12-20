package com.example.baselibapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dl.baselib.TestLib2
import com.example.baselibapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text="Test"

        TestLib2().print2(this)
    }
}