package com.sugarspoon.beaba.data.model

sealed class ItemHome {
    class Subjects(
        val icon: Int,
        val text: Int,
        val color: Int,
        val router: Int
    ): ItemHome()
}