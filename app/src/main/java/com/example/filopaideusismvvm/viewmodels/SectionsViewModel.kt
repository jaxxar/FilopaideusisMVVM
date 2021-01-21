package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.SectionsDao
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class SectionsViewModel @AssistedInject constructor(
    private val sectionsDao: SectionsDao,
    @Assisted private val id: Int
) :
    ViewModel() {

    val sections = sectionsDao.getSections(id).asLiveData()

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(id: Int): SectionsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}