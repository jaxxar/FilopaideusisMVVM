package com.example.filopaideusismvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.filopaideusismvvm.data.SectionsDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SectionsViewModel @AssistedInject constructor(
    sectionsDao: SectionsDao,
    @Assisted private val id: Int
) :
    ViewModel() {

    val sections = sectionsDao.getSections(id).asLiveData()

    companion object {
        fun provideFactory(
            assistedFactory: SectionsViewModelAssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}

@AssistedFactory
interface SectionsViewModelAssistedFactory {
    fun create(id: Int): SectionsViewModel
}