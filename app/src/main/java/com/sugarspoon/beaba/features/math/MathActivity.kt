package com.sugarspoon.beaba.features.math

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.extensions.intentFor

class MathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)
    }


    companion object {
        fun intent(context: Context) = context.intentFor<MathActivity>()
    }
}