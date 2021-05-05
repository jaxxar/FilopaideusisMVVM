package com.example.filopaideusismvvm.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListQuestionData(
    val list: MutableList<QuestionData>?
) : Parcelable