package com.example.filopaideusismvvm.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Question")
@Parcelize
data class QuestionData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val question: String? = null,
    val answer1: String? = null,
    val answer2: String? = null,
    val answer3: String? = null,
    val answer4: String? = null,
    val correctAnswer: String? = null,
    var submittedAnswer: String? = null,
    val hint: String? = null,
    val chapter: String? = null
) : Parcelable