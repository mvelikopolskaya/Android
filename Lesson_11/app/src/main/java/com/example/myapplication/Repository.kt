package com.example.myapplication


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

private var prefs : SharedPreferences? = null
private val editor = prefs?.edit()
private var localVariable: String? = null
const val PREFERENCE_NAME = "prefs_name"
const val TEXT_EDITOR_CONTENT = "text_editor_content"

class Repository {

    fun saveText(text: String) {
        localVariable = text
        editor?.putString(TEXT_EDITOR_CONTENT, text)?.apply()
    }

    fun getText(context: Context) : String {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!
            else -> context.getString(R.string.data_info)
        }
    }

    fun clearText() {
        editor?.clear()
        localVariable = null
    }

    private fun getDataFromSharedPreference(context: Context): String? {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return prefs?.getString(TEXT_EDITOR_CONTENT, null)
    }

    private fun getDataFromLocalVariable(): String? {
        return localVariable
    }
}