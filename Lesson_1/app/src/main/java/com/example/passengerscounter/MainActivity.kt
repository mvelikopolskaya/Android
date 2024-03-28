package com.example.passengerscounter

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.example.passengerscounter.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.appNotification.setTextColor(Color.GREEN)
        binding.btnDecrease.isEnabled = false

        binding.btnReset.setOnClickListener{
            counter = 0
            binding.btnReset.visibility = View.INVISIBLE
            binding.passengerCounter.text = counter.toString()
            binding.appNotification.setTextColor(Color.GREEN)
            binding.appNotification.text = getText(R.string.notification)
            binding.btnDecrease.isEnabled = false
            binding.btnIncrease.isEnabled = true
        }

        binding.btnIncrease.setOnClickListener {
            counter++
            binding.passengerCounter.text = counter.toString()
            binding.appNotification.text = "${50 - counter} seats are available"
            binding.appNotification.setTextColor(Color.BLUE)
            binding.btnReset.visibility = View.INVISIBLE
            binding.btnDecrease.isEnabled = true
            if (counter == 50) {
                binding.btnReset.visibility = View.VISIBLE
                binding.appNotification.text = "All seats are taken"
                binding.appNotification.setTextColor(Color.RED)
                binding.btnIncrease.isEnabled = false
                binding.passengerCounter.text = counter.toString()
            }
        }

        binding.btnDecrease.setOnClickListener {
            counter--
            binding.passengerCounter.text = counter.toString()
            binding.appNotification.text = "${50 - counter} seats are available"
            binding.appNotification.setTextColor(Color.BLUE)
            binding.btnIncrease.isEnabled = true
            binding.btnReset.visibility = View.INVISIBLE
            if(counter == 0) {
                binding.appNotification.text = getText(R.string.notification)
                binding.appNotification.setTextColor(Color.GREEN)
                binding.btnReset.visibility = View.INVISIBLE
                binding.btnDecrease.isEnabled = false
                binding.passengerCounter.text = counter.toString()

            }
        }
    }
}