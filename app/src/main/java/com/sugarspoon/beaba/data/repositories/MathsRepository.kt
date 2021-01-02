package com.sugarspoon.beaba.data.repositories

import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.ItemCount

class MathsRepository {

    fun getCount() = listOf(
        ItemCount(number = 1, fingerRight = R.drawable.ic_finger_one),
        ItemCount(number = 2, fingerRight = R.drawable.ic_finger_two),
        ItemCount(number = 3, fingerRight = R.drawable.ic_finger_three),
        ItemCount(number = 4, fingerRight = R.drawable.ic_finger_four),
        ItemCount(number = 5, fingerRight = R.drawable.ic_finger_five),
        ItemCount(number = 6, fingerLeft = R.drawable.ic_finger_one, fingerRight = R.drawable.ic_finger_five),
        ItemCount(number = 7, fingerLeft = R.drawable.ic_finger_two, fingerRight = R.drawable.ic_finger_five),
        ItemCount(number = 8, fingerLeft = R.drawable.ic_finger_three, fingerRight = R.drawable.ic_finger_five),
        ItemCount(number = 9, fingerLeft = R.drawable.ic_finger_four, fingerRight = R.drawable.ic_finger_five),
        ItemCount(number = 10, fingerLeft = R.drawable.ic_finger_five, fingerRight = R.drawable.ic_finger_five),
    )
}