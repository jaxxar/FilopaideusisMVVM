package com.example.filopaideusismvvm.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.StudentClassDao

class StudentClassViewModel @ViewModelInject constructor(
    private val studentClassDao: StudentClassDao
) : ViewModel() {

    val studentClass = studentClassDao.getStudentClass().asLiveData()
}