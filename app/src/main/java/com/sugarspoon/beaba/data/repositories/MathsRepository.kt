package com.sugarspoon.beaba.data.repositories

import com.sugarspoon.beaba.data.model.ItemCount

class MathsRepository {

    fun getCount() = listOf(
        ItemCount(number = 1),
        ItemCount(number = 2),
        ItemCount(number = 3),
        ItemCount(number = 4),
        ItemCount(number = 5),
        ItemCount(number = 6),
        ItemCount(number = 7),
        ItemCount(number = 8),
        ItemCount(number = 9),
        ItemCount(number = 10)
    )
}