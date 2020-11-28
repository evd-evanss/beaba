package com.sugarspoon.beaba.ui.paint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.PencilColor
import com.sugarspoon.beaba.data.model.StateVisibility
import com.sugarspoon.beaba.data.model.StrokeSize
import com.sugarspoon.beaba.data.repositories.PaintRepository
import kotlinx.coroutines.flow.MutableStateFlow

class PaintViewModel(
    private val repository: PaintRepository
) : ViewModel() {

    val state = PaintViewStates(
        strokePencil = repository.strokePencil,
        displayPaletteDialog = repository.displayColorPicker,
        color = repository.color,
    )

    fun setStrokePencil(stroke: Int) = repository.setStrokePencil(stroke)

    fun setColor(color: Int = R.color.black) = repository.setColor(resource = color)

    fun displayPaletteDialog() = repository.displayColorPicker()


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

    data class PaintViewStates(
        val strokePencil: MutableStateFlow<StrokeSize>? = null,
        val displayPaletteDialog: MutableStateFlow<StateVisibility>? = null,
        val color: MutableStateFlow<PencilColor>? = null
    )
}
