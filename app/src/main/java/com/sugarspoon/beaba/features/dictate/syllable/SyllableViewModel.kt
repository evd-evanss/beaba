package com.sugarspoon.beaba.features.dictate.syllable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sugarspoon.beaba.data.repositories.SyllableRepository
import kotlinx.coroutines.flow.MutableStateFlow

class SyllableViewModel(
    private val repository: SyllableRepository
) : ViewModel(){

    val items = MutableStateFlow(repository.getSyllables())

    @Suppress("UNCHECKED_CAST")
    class Factory constructor(
        private val repository: SyllableRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SyllableViewModel(
                repository = repository
            ) as T
        }
    }

}
