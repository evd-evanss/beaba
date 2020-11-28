package com.sugarspoon.beaba.data.repositories

import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.PencilColor
import com.sugarspoon.beaba.data.model.StateVisibility
import com.sugarspoon.beaba.data.model.StrokeSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PaintRepository {

    val strokePencil = MutableStateFlow(StrokeSize(INITIAL_THICKNESS))
    val color = MutableStateFlow(PencilColor(R.color.black))
    val displayColorPicker = MutableStateFlow(StateVisibility(visibility = false))

    fun setStrokePencil(thickness: Int) {
        strokePencil.value = StrokeSize(thickness.toFloat())
    }

    fun displayColorPicker() {
        CoroutineScope(Main).launch {
            color.emit(color.replayCache.first())
            displayColorPicker.emit(StateVisibility(visibility = true))
        }
    }

    fun setColor(resource: Int) {
        color.value = PencilColor(resource)
    }

    companion object {
        private const val INITIAL_THICKNESS = 5f
    }
}