package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM words ORDER BY word LIMIT 5")
    fun getAll(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordInput: Word)

    @Query("DELETE FROM words")
    suspend fun delete()

    @Query("UPDATE words SET count = count + 1 WHERE word LIKE :wordInput")
    suspend fun update(wordInput: String)

    @Query("SELECT word FROM words WHERE word LIKE :wordInput")
    suspend fun search(wordInput: String): String?
}