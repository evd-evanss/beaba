package com.sugarspoon.beaba.data.repositories

import com.sugarspoon.beaba.data.model.ItemSyllable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SyllableRepository {

    val itemSyllable = MutableStateFlow(getEmpty())
    val listItems = getEmpty()

    fun getEmpty() = mutableListOf(
        ItemSyllable("BA", true)
    )

    fun setNextItem(position: Int) {
        CoroutineScope(Main).launch {
            listItems.add(getItem(listItems.size + position))
            itemSyllable.emit(listItems)
        }
    }

    private fun getItem(count: Int): ItemSyllable {
        return when(count) {
            0 -> ItemSyllable("BE", true)
            1-> ItemSyllable("BE", true)
            2 -> ItemSyllable("BI", true)
            3 -> ItemSyllable("BO", true)
            4 -> ItemSyllable("BU", true)
            else -> ItemSyllable("BÃO", true)
        }
    }

    companion object {

    }
}