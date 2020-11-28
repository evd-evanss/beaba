package com.sugarspoon.beaba.ui.paint

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.commom.EraserDialog
import com.sugarspoon.beaba.commom.PaletteDialog
import com.sugarspoon.beaba.data.model.StateVisibility
import com.sugarspoon.beaba.data.repositories.PaintRepository
import com.sugarspoon.beaba.utils.extensions.intentFor
import com.sugarspoon.beaba.utils.extensions.setVisible
import kotlinx.android.synthetic.main.activity_paint.*
import kotlinx.coroutines.flow.collect

class PaintActivity : BaseActivity() {

    private val factory = PaintViewModel.Factory(PaintRepository())
    private val viewModel by viewModels<PaintViewModel> { factory }
    private var progressPencil = 0
    private var progressEraser = 0
    private var pencilColor: Int = R.color.black

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        setupListeners()
        setupCollectors()
    }

    private fun setupListeners() {
        paintView.run {
            onPencilClicked {
                viewModel.displayPaletteDialog()
                paintView.setStrokePencil(progressPencil.toFloat())
            }
            onBlankSheetClicked {
                paintView.onPreviewClicked()
            }
            onCloseClicked {
                finish()
            }
        }
    }

    private fun setupCollectors() {
        this.lifecycleScope.run {
            launchWhenResumed {
                viewModel.state.strokePencil?.collect {
                    paintView.setStrokePencil(it.size)
                    progressPencil = it.size.toInt()
                }
            }
            launchWhenResumed {
                viewModel.state.displayPaletteDialog?.collect {
                    displayPaletteDialog(it)
                }
            }
            launchWhenResumed {
                viewModel.state.color?.collect {
                    paintView.setColor(it.color)
                }
            }
        }
    }

    private fun displayPaletteDialog(stateVisibility: StateVisibility) {
        PaletteDialog(
            context = this,
            title = getString(R.string.palette_colors),
            colorPicker = {
                viewModel.setColor(it)
            },
            seekBarPencil = {
                viewModel.setStrokePencil(it)
            },
            progress = progressPencil
        ).showDialog(stateVisibility.visibility)
    }

    companion object {
        fun intent(context: Context) = context.intentFor<PaintActivity>()
    }
}