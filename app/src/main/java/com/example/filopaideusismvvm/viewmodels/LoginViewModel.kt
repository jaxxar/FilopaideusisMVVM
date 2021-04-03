package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {

    var usernameLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val updateStudentClass = repository.updateStudentClass().asLiveData()
    val updateSections = repository.updateSections().asLiveData()
    val updateQuestions = repository.updateQuestions().asLiveData()

    fun validateData(username: String) {
        usernameLiveData.value = username.isNotEmpty()
    }
}