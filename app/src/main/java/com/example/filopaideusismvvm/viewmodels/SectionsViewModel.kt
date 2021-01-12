package com.example.filopaideusismvvm.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.filopaideusismvvm.data.SectionsDao

class SectionsViewModel @ViewModelInject constructor(private val sectionsDao: SectionsDao) :
    ViewModel() {
}