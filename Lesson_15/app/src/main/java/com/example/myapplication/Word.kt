package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val wordOutput: String,
    @ColumnInfo(name = "count")
    val count: Int = 0
)

