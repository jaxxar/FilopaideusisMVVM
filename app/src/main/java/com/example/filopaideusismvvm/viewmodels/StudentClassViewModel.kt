package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.StudentClassDao
import com.example.filopaideusismvvm.utilities.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class StudentClassViewModel @Inject internal constructor(
    studentClassDao: StudentClassDao
) : ViewModel() {
    val searchQuery = MutableStateFlow(EMPTY_STRING)
    private val taskFlow = searchQuery.flatMapLatest {
        studentClassDao.getStudentClass(it)
    }

    val studentClass = taskFlow.asLiveData()
}