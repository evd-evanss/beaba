package com.sugarspoon.beaba.data

import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.features.main.ItemHome

class HomeRepository {

    fun getHomeItems() = listOf(
        ItemHome.Subjects(
            icon = R.drawable.ic_teacher,
            text = R.string.dictate_vowels,
            color = R.color.red,
            router = DICTATE
        ),
        ItemHome.Subjects(
            icon = R.drawable.ic_teacher_man,
            text = R.string.dictate_syllables,
            color = R.color.purple_200,
            router = DICTATE
        ),
        ItemHome.Subjects(
            icon = R.drawable.ic_abacus,
            text = R.string.math_basic,
            color = R.color.green,
            router = MATHS
        ),
        ItemHome.Subjects(
            icon = R.drawable.ic_chemistry,
            text = R.string.paint_free,
            color = R.color.blue,
            router = PAINT
        )
    )

    companion object {
        const val DICTATE = 1
        const val MATHS = 2
        const val PAINT = 3
    }

}