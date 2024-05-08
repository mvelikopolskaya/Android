package com.example.myapplication


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log

const val PREFERENCE_NAME = "prefs_name"
const val TEXT_EDITOR_CONTENT = "text_editor_content"

class Repository(val context : Context) {
    private var prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
    private val editor = prefs.edit()
    private var localVariable: String? = null

    fun saveText(text: String) {
        localVariable = text
        editor.putString(TEXT_EDITOR_CONTENT, text).apply()
    }

    fun getText() : String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference() != null -> getDataFromSharedPreference()!!
            else -> context.getString(R.string.data_info)
        }
    }

    fun clearText() {
        editor.clear().apply()
        localVariable = null
    }
    private fun getDataFromSharedPreference(): String? {
        return prefs.getString(TEXT_EDITOR_CONTENT, null)
    }

    private fun getDataFromLocalVariable(): String? {
        return localVariable
    }
}