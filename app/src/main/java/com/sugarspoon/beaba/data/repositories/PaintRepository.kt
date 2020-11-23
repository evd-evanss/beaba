package com.sugarspoon.beaba.data.repositories

import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.PencilColor
import com.sugarspoon.beaba.data.model.StateVisibility
import com.sugarspoon.beaba.data.model.StrokeSize
import kotlinx.coroutines.flow.MutableStateFlow

class PaintRepository {

    val strokePencil = MutableStateFlow(StrokeSize(INITIAL_THICKNESS))
    val strokeEraser = MutableStateFlow(StrokeSize(INITIAL_THICKNESS))
    val color = MutableStateFlow(PencilColor(R.color.black))
    val seekBarPencilVisibility = MutableStateFlow(StateVisibility(visibility = false))
    val seekBarEraserVisibility = MutableStateFlow(StateVisibility(visibility = false))
    val displayColorPicker = MutableStateFlow(StateVisibility(visibility = false))
    private var handlePencil = false
    private var handleEraser = false

    fun setStrokePencil(thickness: Int) {
        strokePencil.value = StrokeSize(thickness.toFloat())
    }

    fun setStrokeEraser(thickness: Int) {
        strokeEraser.value = StrokeSize(thickness.toFloat())
    }

    fun handlePencilSeekBar() {
        handlePencil = !handlePencil
        seekBarPencilVisibility.value = StateVisibility(handlePencil)
        if(handleEraser) {
            seekBarEraserVisibility.value = StateVisibility(!handleEraser)
        }
    }

    fun handleEraserSeekBar() {
        handleEraser = !handleEraser
        seekBarEraserVisibility.value = StateVisibility(handleEraser)
        if(handlePencil) {
            seekBarPencilVisibility.value = StateVisibility(!handlePencil)
        }
    }

    fun displayColorPicker() {
        displayColorPicker.value = StateVisibility(visibility = true)
    }

    fun setColor(resource: Int) {
        color.value = PencilColor(resource)
    }

    fun setEraseColor(resource: Int = R.color.white) {
        color.value = PencilColor(resource)
    }

    companion object {
        private const val INITIAL_THICKNESS = 5f
    }
}