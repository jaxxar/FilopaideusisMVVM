package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.QuestionDao
import com.example.filopaideusismvvm.data.QuestionData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class QuestionViewModel @AssistedInject constructor(
    questionDao: QuestionDao,
    @Assisted private val id: Int
) :
    ViewModel() {

    var totalQuestions = questionDao.getTotalQuestion(id).asLiveData()
    val question = questionDao.getQuestion(id).asLiveData()
    private var questionList = mutableListOf<QuestionData>()
    private var index: Int = 0

    fun checkChecked(questionData: QuestionData): Int {
        return when {
            questionData.checked1 == true -> 1
            questionData.checked2 == true -> 2
            questionData.checked3 == true -> 3
            questionData.checked4 == true -> 4
            else -> -1
        }
    }

    fun addToList(questionData: QuestionData) {
        var questionFound = false
        if (questionList.size != 0) {
            for (i in 0 until questionList.size) {
                questionFound = false
                if (questionList[i].id == questionData.id) {
                    questionList.removeAt(i)
                    questionList.add(i, questionData)
                    questionFound = true
                    break
                }
            }
        }
        if (!questionFound) {
            questionList.add(index, questionData)
            index++
        }
    }

    fun findUnchecked(): Int {
        if (questionList.size != 0) {
            for (i in question.value!!.indices) {
                var itemNotFound = true
                var position = 0
                for (j in 0 until questionList.size) {
                    if (questionList[j].id == question.value!![i].id) {
                        itemNotFound = false
                    } else position = i
                }
                if (itemNotFound) return position
            }
        }
        return 0
    }

    fun returnListSize(): Int {
        return if (questionList.size != 0) {
            questionList.size
        } else 0
    }

    fun returnList(): MutableList<QuestionData> {
        return questionList
    }

    companion object {
        fun provideFactory(
            assistedFactory: QuestionViewModelAssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}

@AssistedFactory
interface QuestionViewModelAssistedFactory {
    fun create(id: Int): QuestionViewModel
}