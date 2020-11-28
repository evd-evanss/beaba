package com.sugarspoon.beaba.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.utils.extensions.setVisible
import kotlinx.android.synthetic.main.widget_paint.view.*

class PaintView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_paint, this, true)
        setListeners()
    }

    private var strokePencil: Float = 0f

    private fun setListeners() {
        paintViewPreviewClose.setOnClickListener {
            containerPreview.setVisible(false)
        }
        paintViewUndo.setOnClickListener {
            undo()
        }
    }

    fun onCloseClicked(listener: (View) -> Unit) =
        paintViewClose.setOnClickListener {
            listener(it)
        }

    fun onPencilClicked(listener: (View) -> Unit) =
        widgetPalettePencil.setOnClickListener {
            listener(it)
        }

    fun onBlankSheetClicked(listener: (View) -> Unit) =
        widgetPaletteBlankSheet.setOnClickListener {
            listener(it)
        }

    fun setColor(color: Int) {
        widgetPaintCanvas.setColor(ContextCompat.getColor(context, color))
    }

    fun setStrokePencil(size: Float) {
        strokePencil = size
        widgetPaintCanvas.setStrokeWidth(strokePencil)
        widgetPaintCanvas.undo()
    }

    private fun undo() {
        widgetPaintCanvas.undo()
    }

    private fun getBitmap() = widgetPaintCanvas.getBitmap()

    fun onPreviewClicked() {
        containerPreview.setVisible(true)
        paintViewPreview.setImageBitmap(getBitmap())
    }

}