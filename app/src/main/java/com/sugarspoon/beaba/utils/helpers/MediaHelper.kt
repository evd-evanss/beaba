package com.sugarspoon.beaba.utils.helpers

import android.content.Context
import android.media.MediaPlayer
import com.sugarspoon.beaba.R

class MediaHelper(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    fun play(sound: Int = R.raw.victory) {
        val mediaPlayer= MediaPlayer.create(context, sound)
        mediaPlayer.start()
    }

    fun stop() {
        mediaPlayer?.stop()
    }
}