package com.sugarspoon.beaba.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager

class BaseBottomDialog : BaseDialog {
    constructor(context: Context) : super(context)

    constructor(context: Context, theme: Int) : super(context, theme)

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) :
        super(context, cancelable, cancelListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomLayout()
    }

    private fun setupBottomLayout() {
        val attrs = window?.attributes
        attrs?.gravity = Gravity.BOTTOM
        attrs?.flags = attrs?.flags?.and(WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv())
        window?.attributes = attrs
    }
}
