package com.sugarspoon.beaba.ui.dictate.syllable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sugarspoon.beaba.data.model.ItemSyllable
import com.sugarspoon.beaba.data.repositories.SyllableRepository

class SyllableViewModel(
    private val repository: SyllableRepository
) : ViewModel(){

    val items = repository.itemSyllable

    fun setNextItem(position: Int) = repository.setNextItem(position)

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
