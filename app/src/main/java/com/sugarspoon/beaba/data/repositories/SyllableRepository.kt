package com.sugarspoon.beaba.data.repositories

import com.sugarspoon.beaba.data.model.ItemSyllable

class SyllableRepository {

    fun getSyllables() = listOf(
        ItemSyllable("BA"),
        ItemSyllable("BE"),
        ItemSyllable("BI"),
        ItemSyllable("BO"),
        ItemSyllable("BU"),
        ItemSyllable("BÃO"),
    )
}