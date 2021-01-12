package com.example.filopaideusismvvm.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question")
    fun getQuestion(): Flow<List<QuestionData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionData: QuestionData)

    @Update()
    suspend fun update(questionData: QuestionData)
}