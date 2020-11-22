package com.sugarspoon.beaba.features.dictate.vowel

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.extensions.intentFor
import com.sugarspoon.beaba.utils.helpers.MediaHelper
import kotlinx.android.synthetic.main.activity_dictate.*

class DictateActivity : BaseActivity(), TextToSpeech.OnInitListener {

    private lateinit var textToSpeech : TextToSpeech

    private val mediaPlayer by lazy {
        MediaHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictate)
        setToolbar(getString(R.string.dictate_toolbar_title), true)
        setupUi()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        textToSpeech = TextToSpeech(this, this)
    }

    private fun setupUi() {
        dictatePanelDv.startAnimation()
    }

    private fun setupListeners() {
        dictatePanelDv.run {
            onClickLetterA {
                textToSpeech.speak(it,TextToSpeech.QUEUE_FLUSH,null,null)
            }
            onClickLetterE {
                textToSpeech.speak(it,TextToSpeech.QUEUE_FLUSH,null,null)
            }
            onClickLetterI {
                textToSpeech.speak(it,TextToSpeech.QUEUE_FLUSH,null,null)
            }
            onClickLetterO {
                textToSpeech.speak(it,TextToSpeech.QUEUE_FLUSH,null,null)
            }
            onClickLetterU {
                textToSpeech.speak(it,TextToSpeech.QUEUE_FLUSH,null,null)
            }
            onClickLetterAO {
                textToSpeech.speak(it,TextToSpeech.QUEUE_FLUSH,null,null)
                mediaPlayer.play()
            }
        }
    }

    override fun onInit(result: Int) {
        if(result == TextToSpeech.SUCCESS) {
            textToSpeech.setPitch(1.5f)
            textToSpeech.setSpeechRate(0.05f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dictatePanelDv.stopAnimation()
    }

    companion object {
        fun intent(context: Context) = context.intentFor<DictateActivity>()
    }
}