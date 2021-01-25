package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.QuestionDao
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class QuestionViewModel @AssistedInject constructor(
    questionDao: QuestionDao,
    @Assisted private val id: Int
) :
    ViewModel() {

    var totalQuestions = questionDao.getTotalQuestion(id).asLiveData()
    val question = questionDao.getQuestion(id).asLiveData()

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(id: Int): QuestionViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}