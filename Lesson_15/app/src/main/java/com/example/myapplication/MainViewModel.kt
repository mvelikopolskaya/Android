package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {

    val dictionary = this.wordDao.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun onAddBtn(wordInput: String) : Boolean{
        if(isWordMatches(wordInput)) {
            viewModelScope.launch {
                if(wordDao.search(wordInput).isNullOrEmpty()){
                    wordDao.insert(Word(
                        wordInput,
                        1))
                } else wordDao.update(wordInput)
            }
            return true
        }
        return false
    }

    private fun isWordMatches(word: String): Boolean{
        val regex = Regex("^[A-Za-zа-яёА-ЯЁ-]+$")
        return word.isNotEmpty() && word.matches(regex)
    }

    fun onClearBtn(){
        viewModelScope.launch {
            wordDao.delete()
        }
    }
}