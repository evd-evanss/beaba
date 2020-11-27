package com.sugarspoon.beaba.ui.paint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.repositories.PaintRepository

class PaintViewModel(
    private val repository: PaintRepository
) : ViewModel(){

    val strokePencil = repository.strokePencil
    val strokeEraser = repository.strokeEraser
    val seekBarPencilVisibility = repository.seekBarPencilVisibility
    val seekBarEraserVisibility = repository.seekBarEraserVisibility
    val displayColorPicker = repository.displayColorPicker
    val displayEraserPicker = repository.displayEraserPicker
    val color = repository.color

    fun setStrokePencil(stroke: Int) = repository.setStrokePencil(stroke)

    fun setStrokeEraser(stroke: Int) = repository.setStrokeEraser(stroke)

    fun handlePencilSeekBar() = repository.handlePencilSeekBar()

    fun handleEraserSeekBar() = repository.handleEraserSeekBar()

    fun setColor(color: Int = R.color.black) = repository.setColor(resource = color)

    fun displayColorPicker() = repository.displayColorPicker()

    fun displayEraserPicker() = repository.displayEraserPicker()

    fun setEraser() = repository.setEraseColor()

    @Suppress("UNCHECKED_CAST")
    class Factory constructor(
        private val repository: PaintRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PaintViewModel(
                repository = repository
            ) as T
        }
    }
}
