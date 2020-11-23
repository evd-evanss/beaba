package com.sugarspoon.beaba.features.math

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sugarspoon.beaba.data.repositories.MathsRepository
import kotlinx.coroutines.flow.MutableStateFlow

class MathViewModel(
    private val repository: MathsRepository
) : ViewModel(){

    val items = MutableStateFlow(repository.getCount())

    @Suppress("UNCHECKED_CAST")
    class Factory constructor(
        private val repository: MathsRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MathViewModel(
                repository = repository
            ) as T
        }
    }
}
