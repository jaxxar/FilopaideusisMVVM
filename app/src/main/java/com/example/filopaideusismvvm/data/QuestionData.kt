package com.example.filopaideusismvvm.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filopaideusismvvm.utilities.TABLE_QUESTIONS
import kotlinx.parcelize.Parcelize

@Entity(tableName = TABLE_QUESTIONS)
@Parcelize
data class QuestionData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "list_questions_id") val listQuestionsId: Int? = 0,
    @ColumnInfo(name = "question") val question: String? = null,
    @ColumnInfo(name = "answer_1") val answer1: String? = null,
    @ColumnInfo(name = "answer_2") val answer2: String? = null,
    @ColumnInfo(name = "answer_3") val answer3: String? = null,
    @ColumnInfo(name = "answer_4") val answer4: String? = null,
    @ColumnInfo(name = "correct_answer") val correctAnswer: String? = null,
    @ColumnInfo(name = "submitted_answer") var submittedAnswer: String? = null,
    @ColumnInfo(name = "checked_1") var checked1: Boolean? = false,
    @ColumnInfo(name = "checked_2") var checked2: Boolean? = false,
    @ColumnInfo(name = "checked_3") var checked3: Boolean? = false,
    @ColumnInfo(name = "checked_4") var checked4: Boolean? = false,
    @ColumnInfo(name = "hint") val hint: String? = null,
    @ColumnInfo(name = "chapter") val chapter: String? = null,
    @ColumnInfo(name = "question_type") val questionType: Int = 0
) : Parcelable