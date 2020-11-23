package com.sugarspoon.beaba.commom

import android.content.Context
import android.os.Bundle
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseDialog
import kotlinx.android.synthetic.main.color_palette_view.*

class ColorPickerDialog(context: Context) : BaseDialog(context) {

    constructor(context: Context, title: String, colorPicker: ((Int) -> Unit)) : this(context) {
        this.title = title
        this.colorPicker = colorPicker
    }

    private var title: String? = null
    private var dismissAction: (() -> Unit)? = null
    private var colorPicker: ((Int) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.color_palette_view)
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
