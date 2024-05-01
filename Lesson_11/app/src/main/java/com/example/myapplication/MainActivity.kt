package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private var repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            val updatedText = binding.textEdit.text.toString()
            repository.saveText(updatedText)
            binding.textView.text = repository.getText(this)
        }

        binding.clearBtn.setOnClickListener {
            repository.clearText()
            binding.textEdit.text.clear()
            binding.textView.text = repository.getText(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val savedText = preferences.getString(TEXT_EDITOR_CONTENT, null)
        binding.textView.text = savedText
    }

    override fun onPause() {
        super.onPause()
        val preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val edit = preferences.edit()
        edit.putString(TEXT_EDITOR_CONTENT, binding.textView.text.toString()).apply()
    }
}