package com.example.filopaideusismvvm.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ListQuestionData")
@Parcelize
data class ListQuestionData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val list: MutableList<QuestionData>?
) : Parcelable