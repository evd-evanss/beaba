package com.sugarspoon.beaba.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sugarspoon.beaba.data.repositories.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel(){

    val items = MutableStateFlow(repository.getHomeItems())

    @Suppress("UNCHECKED_CAST")
    class Factory constructor(
        private val repository: HomeRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(
                repository = repository
            ) as T
        }
    }

}
