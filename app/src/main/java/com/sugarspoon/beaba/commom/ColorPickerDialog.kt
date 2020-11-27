package com.sugarspoon.beaba.commom

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseDialog
import kotlinx.android.synthetic.main.palette_view.*
import kotlinx.android.synthetic.main.palette_view.widgetPaintPencilSeekBar
import kotlinx.android.synthetic.main.widget_paint.*

class ColorPickerDialog(context: Context) : BaseDialog(context) {

    constructor(
        context: Context,
        title: String,
        colorPicker: ((Int) -> Unit),
        seekBarPencil: ((Int) -> Unit),
        seekBarEraser: ((Int) -> Unit),
        onClickPencil: ((View) -> Unit),
        onClickEraser: ((View) -> Unit),
    ) : this(context) {
        this.title = title
        this.colorPicker = colorPicker
        this.seekBarPencil = seekBarPencil
        this.seekBarEraser = seekBarEraser
        this.onClickPencil = onClickPencil
        this.onClickEraser = onClickEraser
    }

    private var title: String? = null
    private var dismissAction: (() -> Unit)? = null
    private var colorPicker: ((Int) -> Unit)? = null
    private var seekBarPencil: ((Int) -> Unit)? = null
    private var seekBarEraser: ((Int) -> Unit)? = null
    private var onClickPencil: ((View) -> Unit)? = null
    private var onClickEraser: ((View) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.palette_view)
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
        title_color_picker.text = title
    }

    private fun setListeners() {
        image_color_black.setOnClickListener {
            this.colorPicker?.let { it1 -> it1(R.color.black) }
            dismiss()
        }
        image_color_blue.setOnClickListener {
            colorPicker?.let { it1 -> it1(R.color.blue) }
            dismiss()
        }
        image_color_brown.setOnClickListener {
            colorPicker?.let { it1 -> it1(R.color.brown) }
            dismiss()
        }
        image_color_green.setOnClickListener {
            colorPicker?.let { it1 -> it1(R.color.green) }
            dismiss()
        }
        image_color_pink.setOnClickListener {
            colorPicker?.let { it1 -> it1(R.color.pink) }
            dismiss()
        }
        image_color_red.setOnClickListener {
            colorPicker?.let { it1 -> it1(R.color.red) }
            dismiss()
        }
        image_color_yellow.setOnClickListener {
            colorPicker?.let { it1 -> it1(R.color.yellow) }
            dismiss()
        }
        widgetPaintPencilSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBarPencil?.let { it(progress) }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        widgetPaintEraserSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBarEraser?.let { it(progress) }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        widgetPaintPencil.setOnClickListener {
            onClickPencil?.let { it1 -> it1(it) }
        }
        widgetPaintEraser.setOnClickListener {
            onClickPencil?.let { it1 -> it1(it) }
        }
    }

    fun setTitle(title: String?): ColorPickerDialog {
        this.title = title
        return this
    }

    fun setDismissAction(dismissAction: () -> Unit): ColorPickerDialog {
        this.dismissAction = dismissAction
        return this
    }

    fun showDialog(showDialog: Boolean) {
        if (showDialog) {
            show()
        }
    }
}
