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

    fun shareBody(results: ListQuestionData, username: String, question: String, correctAnswer: String, submittedAnswer: String): String {
        var shareBody = username + " " + calculateCorrect(results) + "/" + listSize(results) + "\n"
        for (i in 0 until listSize(results)) {
            shareBody += "\n" + question + (results.list?.get(i)?.question ?: "") + "\n" + submittedAnswer + (results.list?.get(i)?.submittedAnswer
                ?: "") + "\n" + correctAnswer + (results.list?.get(i)?.correctAnswer ?: "") + "\n"
        }
        return shareBody
    }
}