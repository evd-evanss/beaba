package com.sugarspoon.beaba.commom

import android.content.Context
import android.os.Bundle
import android.widget.SeekBar
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseDialog
import kotlinx.android.synthetic.main.eraser_view.*

class ErasePickerDialog(context: Context) : BaseDialog(context) {

    constructor(
        context: Context,
        title: String,
        seekBarEraser: ((Int) -> Unit),
    ) : this(context) {
        this.title = title
        this.seekBarEraser = seekBarEraser
    }

    private var title: String? = null
    private var dismissAction: (() -> Unit)? = null
    private var seekBarEraser: ((Int) -> Unit)? = null

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

    fun setTitle(title: String?): ErasePickerDialog {
        this.title = title
        return this
    }

    fun setDismissAction(dismissAction: () -> Unit): ErasePickerDialog {
        this.dismissAction = dismissAction
        return this
    }

    fun showDialog(showDialog: Boolean) {
        if (showDialog) {
            show()
        }
    }
}
