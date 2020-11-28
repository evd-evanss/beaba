package com.sugarspoon.beaba.commom

import android.content.Context
import android.os.Bundle
import android.widget.SeekBar
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseDialog
import kotlinx.android.synthetic.main.eraser_view.*

class EraserDialog(context: Context) : BaseDialog(context) {

    constructor(
        context: Context,
        title: String,
        seekBarEraser: ((Int) -> Unit),
        progress: Int
    ) : this(context) {
        this.title = title
        this.seekBarEraser = seekBarEraser
        this.progress = progress
    }

    private var title: String? = null
    private var dismissAction: (() -> Unit)? = null
    private var seekBarEraser: ((Int) -> Unit)? = null
    private var progress: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eraser_view)
        setWidthToMatchWindowsSize()
        setView()
    }

    override fun dismiss() {
        dismissAction?.invoke()
        super.dismiss()
    }

    private fun setView() {
        setLayout()
        setListeners()
        setProgress()
    }

    private fun setLayout() {
        title_eraser_picker.text = title
    }

    private fun setListeners() {
        widgetPaintEraserSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBarEraser?.let { it(progress) }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun setProgress() {
        widgetPaintEraserSeekBar.progress = progress
    }

    fun setTitle(title: String?): EraserDialog {
        this.title = title
        return this
    }

    fun setDismissAction(dismissAction: () -> Unit): EraserDialog {
        this.dismissAction = dismissAction
        return this
    }

    fun showDialog(showDialog: Boolean) {
        if (showDialog) {
            show()
        }
    }
}
