package com.sugarspoon.beaba.ui.paint

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.commom.ColorPickerDialog
import com.sugarspoon.beaba.commom.ErasePickerDialog
import com.sugarspoon.beaba.data.model.StateVisibility
import com.sugarspoon.beaba.data.repositories.PaintRepository
import com.sugarspoon.beaba.utils.extensions.intentFor
import kotlinx.android.synthetic.main.widget_paint.*
import kotlinx.coroutines.flow.collect

class PaintActivity : BaseActivity() {

    private val factory = PaintViewModel.Factory(PaintRepository())
    private val viewModel by viewModels<PaintViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        setToolbar(getString(R.string.paint_toolbar), true)
        setupListeners()
        setupCollect()
    }

    private fun setupListeners() {
        widgetPaintBlankSheet.setOnClickListener {
            widgetPaintCanvas.reset()
        }
        widgetPalette.setOnClickListener {
            viewModel.displayColorPicker()
        }
        widgetPaletteEraser.setOnClickListener {
            viewModel.displayEraserPicker()
            viewModel.handleEraserSeekBar()
            viewModel.setEraser()
        }
    }

    private fun factoryColorPicker(stateVisibility: StateVisibility) {
        ColorPickerDialog(
            this,
            title = "Paleta de Cores",
            colorPicker = { color ->
                viewModel.setColor(color)
            },
            seekBarPencil = { progress->
                viewModel.setStrokePencil(progress)
            },
            seekBarEraser = { progress->
                viewModel.setStrokeEraser(progress)
            },
            onClickPencil = {
                viewModel.displayColorPicker
                viewModel.handlePencilSeekBar()
            },
            onClickEraser = {
                viewModel.handleEraserSeekBar()
                viewModel.setEraser()
            }
        ).showDialog(stateVisibility.visibility)
    }

    private fun factoryEraserPicker(stateVisibility: StateVisibility) {
        ErasePickerDialog(
            this,
            title = "Borracha",
            seekBarEraser = { progress->
                viewModel.setStrokeEraser(progress)
            }
        ).showDialog(stateVisibility.visibility)
    }

    private fun setupCollect() {
        collectForThickness()
        collectForColors()
    }

    private fun collectForThickness() {
        this.lifecycleScope.launchWhenResumed {
            viewModel.run {
                strokePencil.collect {
                    widgetPaintCanvas.setStrokeWidth(it.size)
                }
            }
        }
        this.lifecycleScope.launchWhenResumed {
            viewModel.run {
                strokeEraser.collect {
                    widgetPaintCanvas.setStrokeWidth(it.size)
                }
            }
        }
    }

    private fun collectForColors() {
        this.lifecycleScope.launchWhenResumed {
            viewModel.run {
                displayEraserPicker.collect {
                    factoryEraserPicker(it)
                }
            }
        }
        this.lifecycleScope.launchWhenResumed {
            viewModel.run {
                color.collect {
                    widgetPaintCanvas.setColor(ContextCompat.getColor(applicationContext, it.color))
                }
            }
        }
        this.lifecycleScope.launchWhenResumed {
            viewModel.run {
                displayColorPicker.collect {
                    factoryColorPicker(it)
                }
            }
        }
    }

    companion object {
        fun intent(context: Context) = context.intentFor<PaintActivity>()
    }
}