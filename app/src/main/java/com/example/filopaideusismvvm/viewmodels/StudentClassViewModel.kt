package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.StudentClassDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentClassViewModel @Inject internal constructor(
    studentClassDao: StudentClassDao
) : ViewModel() {

    val studentClass = studentClassDao.getStudentClass().asLiveData()
}