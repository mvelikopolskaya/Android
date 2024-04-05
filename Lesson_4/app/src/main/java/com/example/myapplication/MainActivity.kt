package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.size
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.isEnabled = false

        val points = Random.nextInt(101)
        binding.pointsInt.text = "$points/100"
        binding.pointsBar.progress = points

        binding.save.setOnClickListener {
            if (isNameValid() && isPhoneValid() && isGenderSelected() && isNotificationSelected()) {
                showSnackbar(R.string.save_notification_success)
            }
        }

        binding.login.addTextChangedListener {
            saveInfo()
        }

        binding.phone.addTextChangedListener {
            saveInfo()
        }

        binding.genderOptions.setOnCheckedChangeListener { _, _ ->
            saveInfo()
        }

        binding.receiveNotification.setOnCheckedChangeListener { _, _ ->
            activateNotification()
            saveInfo()
        }

        binding.deviceAuthorization.setOnCheckedChangeListener { _, _ ->
            saveInfo()
        }

        binding.news.setOnCheckedChangeListener { _, _ ->
            saveInfo()
        }
    }

    private fun isPhoneValid() : Boolean {
        return binding.phone.text!!.isNotEmpty()
    }

    private fun isNameValid() : Boolean {
        return !(binding.login.text.isNullOrEmpty() && binding.userLogin.size > 40)
    }

    private fun isGenderSelected() : Boolean {
        return binding.female.isChecked || binding.male.isChecked
    }

    private fun isNotificationSelected() : Boolean {
        return !binding.receiveNotification.isChecked || (binding.receiveNotification.isChecked && (binding.deviceAuthorization.isChecked || binding.news.isChecked))
    }

    private fun showSnackbar(message: Int) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun activateNotification() {
        if(!binding.receiveNotification.isChecked){
            binding.deviceAuthorization.isEnabled = false
            binding.deviceAuthorization.isChecked = false
            binding.news.isEnabled = false
            binding.news.isChecked = false
        }else {
            binding.deviceAuthorization.isEnabled = true
            binding.news.isEnabled = true
        }
    }

    private fun saveInfo() {
        if(isNameValid() && isPhoneValid() && isGenderSelected() && isNotificationSelected())
            binding.save.isEnabled = true
    }
}