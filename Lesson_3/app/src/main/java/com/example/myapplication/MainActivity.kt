package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}