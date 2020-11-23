package com.sugarspoon.beaba.widgets

import android.graphics.Path
import java.io.Serializable
import java.io.Writer

interface Action : Serializable {
    fun perform(path: Path)
    fun perform(writer: Writer)
    fun getTargetX(): Float
    fun getTargetY(): Float
}
