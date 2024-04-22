package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import quiz.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}