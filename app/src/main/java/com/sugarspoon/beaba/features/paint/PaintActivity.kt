package com.sugarspoon.beaba.features.paint

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.extensions.intentFor

class PaintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
    }

    companion object {
        fun intent(context: Context) = context.intentFor<PaintActivity>()
    }
}