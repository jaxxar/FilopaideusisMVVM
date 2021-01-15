package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var usernameLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun validateData(username: String) {
        usernameLiveData.value = username.isNotEmpty()
    }

}