package com.sugarspoon.beaba.features.main

sealed class ItemHome {
    class Subjects(
        val icon: Int,
        val text: Int,
        val color: Int,
        val router: Int
    ): ItemHome()
}