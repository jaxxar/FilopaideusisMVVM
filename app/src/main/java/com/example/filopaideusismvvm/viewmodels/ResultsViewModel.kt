package com.example.filopaideusismvvm.viewmodels

import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModel
import com.example.filopaideusismvvm.data.ListQuestionData

class ResultsViewModel : ViewModel() {

    private val timestamp = System.currentTimeMillis() / 1000

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

    fun getPercent(results: ListQuestionData): Int {
        return if (results.list?.size == 0) 0
        else {
            val percentage = (calculateCorrect(results) * 100) / listSize(results)
            percentage
        }
    }

    fun listSize(results: ListQuestionData): Int {
        return results.list?.size!!
    }

    fun shareBody(results: ListQuestionData, username: String, studentClass: String, topic: String, question: String, correctAnswer: String, submittedAnswer: String, time: String): String {
        var shareBody = username + " " + calculateCorrect(results) + "/" + listSize(results) + "\n" + studentClass + " " + topic + "\n" + time + "\n"
        for (i in 0 until listSize(results)) {
            shareBody += "\n" + question + results.list?.get(i)?.question.let {
                HtmlCompat.fromHtml(
                    it!!,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            } + "\n" + submittedAnswer + results.list?.get(i)?.submittedAnswer.let {
                HtmlCompat.fromHtml(
                    it!!,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            } + "\n" + correctAnswer + results.list?.get(i)?.correctAnswer.let { HtmlCompat.fromHtml(it!!, HtmlCompat.FROM_HTML_MODE_LEGACY) } + "\n"
        }
        return shareBody
    }

    fun calculateTime(startTime: Long): Long {
        return timestamp - startTime
    }
}