package com.sugarspoon.beaba.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sugarspoon.beaba.R
import kotlinx.android.synthetic.main.widget_syllables.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class DictateView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_syllables, this, true)
        viewBlink = letterA
    }

    private var job = Job()
    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    private var viewBlink: TextView? = null

    fun onClickLetterA(onClick: (String) -> Unit) = letterA.setOnClickListener {
        onClick(context.getString(R.string.letter_a))
        viewBlink = letterE
        letterA.background?.setTint(ContextCompat.getColor(context, R.color.green))
    }

    fun onClickLetterE(onClick: (String) -> Unit) = letterE.setOnClickListener {
        onClick(context.getString(R.string.letter_e))
        viewBlink = letterI
        letterE.background?.setTint(ContextCompat.getColor(context, R.color.green))
    }

    fun onClickLetterI(onClick: (String) -> Unit) = letterI.setOnClickListener {
        onClick(context.getString(R.string.letter_i))
        viewBlink = letterO
        letterI.background?.setTint(ContextCompat.getColor(context, R.color.green))
    }

    fun onClickLetterO(onClick: (String) -> Unit) = letterO.setOnClickListener {
        onClick(context.getString(R.string.letter_o))
        viewBlink = letterU
        letterO.background?.setTint(ContextCompat.getColor(context, R.color.green))
    }

    fun onClickLetterU(onClick: (String) -> Unit) = letterU.setOnClickListener {
        onClick(context.getString(R.string.letter_u))
        viewBlink = letterAO
        letterU.background?.setTint(ContextCompat.getColor(context, R.color.green))
    }

    fun onClickLetterAO(onClick: (String) -> Unit) = letterAO.setOnClickListener {
        onClick(context.getString(R.string.vocalic_ao))
        viewBlink = null
        letterAO.background?.setTint(ContextCompat.getColor(context, R.color.green))
    }

    fun startAnimation() {
        if(job.isActive) job.cancel()
        job = Job()
        val coroutineScope = CoroutineScope(Dispatchers.IO + job + handler)
        coroutineScope.launch {
            while (isActive) {
                turnOn()
                delay(300)
                turnOff()
                delay(300)
                Log.d("COROUTINE_", "isActive")
            }
        }
    }

    fun stopAnimation() {
        job.cancel()
    }


    private fun turnOn() {
        CoroutineScope(Main).launch {
            viewBlink?.background?.setTint(ContextCompat.getColor(context, R.color.green))
        }
    }

    private fun turnOff() {
        CoroutineScope(Main).launch {
            viewBlink?.background?.setTint(ContextCompat.getColor(context, R.color.color_gray))
        }
    }
}