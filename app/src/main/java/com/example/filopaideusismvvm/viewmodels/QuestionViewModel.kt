package com.example.filopaideusismvvm.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.filopaideusismvvm.data.QuestionDao

class QuestionViewModel @ViewModelInject constructor(private val questionDao: QuestionDao) :
    ViewModel() {
}