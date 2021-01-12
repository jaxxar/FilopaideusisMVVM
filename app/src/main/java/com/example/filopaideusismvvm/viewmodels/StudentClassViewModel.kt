package com.example.filopaideusismvvm.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.filopaideusismvvm.data.StudentClassDao

class StudentClassViewModel @ViewModelInject constructor(
    private val studentClassDao: StudentClassDao
) : ViewModel() {
}