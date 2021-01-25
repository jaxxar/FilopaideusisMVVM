package com.example.filopaideusismvvm.data

import androidx.room.*
import com.example.filopaideusismvvm.utilities.TABLE_QUESTIONS
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query("SELECT * FROM $TABLE_QUESTIONS WHERE list_questions_id = :listQuestionsId")
    fun getQuestion(listQuestionsId: Int): Flow<List<QuestionData>>

    @Query("SELECT COUNT(*) FROM $TABLE_QUESTIONS WHERE list_questions_id = :listQuestionsId")
    fun getTotalQuestion(listQuestionsId: Int): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionData: QuestionData)

    @Update
    suspend fun update(questionData: QuestionData)
}