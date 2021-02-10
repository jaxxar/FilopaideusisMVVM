package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.example.filopaideusismvvm.data.ListQuestionData

class ResultsViewModel : ViewModel() {

    fun calculateCorrect(results: ListQuestionData): Int {
        return if (results.list?.size == 0) 0
        else {
            var correctAnswers = 0
            for (i in 0 until (results.list?.size ?: 0)) {
                if (results.list?.get(i)?.submittedAnswer == results.list?.get(i)?.correctAnswer) correctAnswers++
            }
            correctAnswers
        }
    }

    fun listSize(results: ListQuestionData): Int {
        return results.list?.size!!
    }

}