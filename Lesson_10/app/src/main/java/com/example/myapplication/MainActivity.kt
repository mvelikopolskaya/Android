package com.example.myapplication


import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TIMER_VALUE = "Timer_value"
private const val START_BTN_VISIBILITY_STATE = "Start_btn_visibility_ state"
private const val STOP_BTN_VISIBILITY_STATE = "Stop_btn_visibility_ state"

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timerValue = 0
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            coroutineScope.launch {
                startCountDown()
            }
        }

        binding.startBtn.setOnClickListener {
            binding.startBtn.visibility = View.GONE
            binding.stopBtn.visibility = View.VISIBLE
            binding.timeSetter
            coroutineScope.launch {
                startCountDown()
                binding.startBtn.visibility = View.VISIBLE
                binding.stopBtn.visibility = View.GONE
            }
        }

        binding.stopBtn.setOnClickListener {
            binding.stopBtn.visibility = View.VISIBLE
            binding.startBtn.visibility = View.GONE
            timerValue = 0
            updateTimeSetter()
            binding.startBtn.visibility = View.VISIBLE
            binding.stopBtn.visibility = View.GONE
        }

        binding.timeSetter.addOnChangeListener { _, _, _ ->
            timerValue = binding.timeSetter.value.toInt()
            binding.countdownProgressNumeric.text = timerValue.toString()
        }
    }

    private fun updateTimeSetter() {
        binding.countdownProgressNumeric.text = timerValue.toString()
        binding.countdownProgressVisual.progress = timerValue
    }

    private fun setTimeSetter() {
        binding.timeSetter.value = timerValue.toFloat()
        binding.timeSetter.value.toInt()
    }

    private suspend fun startCountDown() {
        binding.countdownProgressVisual.max = timerValue
        if (timerValue > 0) {
            while (timerValue > 0) {
                timerValue--
                delay(1000)
                binding.timeSetter.isEnabled = false
                updateTimeSetter()
            }
        }
        binding.timeSetter.isEnabled = true
        setTimeSetter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val timerWeight = timerValue
        val startBtnState = binding.startBtn.visibility
        val stopBtnState = binding.stopBtn.visibility
        outState.putInt(TIMER_VALUE, timerWeight)
        outState.putInt(START_BTN_VISIBILITY_STATE, startBtnState)
        outState.putInt(STOP_BTN_VISIBILITY_STATE, stopBtnState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timerValue = savedInstanceState.getInt(TIMER_VALUE)
        binding.startBtn.visibility = savedInstanceState.getInt(START_BTN_VISIBILITY_STATE)
        binding.stopBtn.visibility = savedInstanceState.getInt(STOP_BTN_VISIBILITY_STATE)
    }
}

