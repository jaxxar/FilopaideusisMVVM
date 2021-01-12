package com.example.filopaideusismvvm.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.QuestionDao

class QuestionViewModel @ViewModelInject constructor(private val questionDao: QuestionDao) :
    ViewModel() {

    val question = questionDao.getQuestion().asLiveData()
}