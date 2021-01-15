package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val username = MutableLiveData<String>("")

}