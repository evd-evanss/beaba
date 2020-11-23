package com.sugarspoon.beaba.features.paint

import android.content.Context
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.commom.ColorPickerDialog
import com.sugarspoon.beaba.data.repositories.PaintRepository
import com.sugarspoon.beaba.utils.extensions.intentFor
import com.sugarspoon.beaba.utils.extensions.setVisible
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
        widgetPaintPencilSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setStrokePencil(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        widgetPaintEraserSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setStrokeEraser(progress)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        widgetPaintPencil.setOnClickListener {
            viewModel.handlePencilSeekBar()
        }
        widgetPaintEraser.setOnClickListener {
            viewModel.handleEraserSeekBar()
            viewModel.setEraser()
        }
        widgetPaintBlankSheet.setOnClickListener {
            widgetPaintCanvas.reset()
        }
        widgetPaintPalette.setOnClickListener {
            viewModel.displayColorPicker()
        }
    }

    private fun factoryColorPicker() {
        ColorPickerDialog(
            this,
            title = "Selecione uma cor",
            colorPicker = { color ->
                viewModel.setColor(color)
            }
        ).show()
    }

    private fun setupCollect() {
        collectForThickness()
        collectForSeekBars()
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

    private fun collectForSeekBars() {
        this.lifecycleScope.launchWhenResumed {
            viewModel.run {
                seekBarPencilVisibility.collect {
                    widgetPaintPencilSeekBar.setVisible(it.visibility)
                }
            }
        }
        this.lifecycleScope.launchWhenResumed {
            viewModel.run {
                seekBarEraserVisibility.collect {
                    widgetPaintEraserSeekBar.setVisible(it.visibility)
                }
            }
        }
    }

    private fun collectForColors() {
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
                    factoryColorPicker()
                }
            }
        }
    }

    companion object {
        fun intent(context: Context) = context.intentFor<PaintActivity>()
    }
}