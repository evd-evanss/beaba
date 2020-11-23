package com.sugarspoon.beaba.utils.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Parcelable
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.commom.DefaultDialogFactory
import java.io.Serializable

fun Activity.removeStatusBar(darkIcons: Boolean = false) {
    window?.run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN +
                if (darkIcons) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else View.SYSTEM_UI_FLAG_VISIBLE
            statusBarColor = Color.TRANSPARENT
        }
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}

fun Activity.hideKeyboard(isVisible: Boolean) {
    if (isVisible) {
        currentFocus?.let {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        }
    }
}

fun Context.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.isNetworkConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun Activity.startActivitySlideTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.anim_close_scale, R.anim.slide_in_left, 1, requestCode)
}

fun Activity.startActivityFadeTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.anim_fade_out, R.anim.anim_fade_in, 1, requestCode)
}

fun Activity.startActivityTransition(
    intent: Intent,
    idAnimationOut: Int,
    idAnimationIn: Int,
    delay: Long,
    requestCode: Int? = null
) {
    if (requestCode == null) {
        Handler().postDelayed({
                                  this.startActivity(intent)
                                  this.overridePendingTransition(idAnimationIn, idAnimationOut)
                              }, delay)
    } else {
        Handler().postDelayed({
                                  this.startActivityForResult(intent, requestCode)
                                  this.overridePendingTransition(idAnimationIn, idAnimationOut)
                              }, delay)
    }
}

fun Activity.finishWithSlideTransition() {
    finish()
    overridePendingTransition(R.anim.anim_open_scale, R.anim.slide_out_right)
}

fun Activity.finishWithFadeTransition() {
    finish()
    overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
}

fun Activity.finishWithTransition(idAnimationOut: Int, idAnimationIn: Int, delay: Long) {
    Handler().postDelayed({
                              this.finish()
                              this.overridePendingTransition(idAnimationIn, idAnimationOut)
                          }, delay)
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Activity.getSerializable(key: String): T {
    return intent.getSerializableExtra(key) as T
}

fun Context.copyToClipboard(content: String) {
    val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val myClip = ClipData.newPlainText("text", content)

    clipBoard?.setPrimaryClip(myClip)
}

// TOAST METHODS
fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun Context.showError(msg: String?) {
    DefaultDialogFactory.createError(
        context = this,
        title = getString(R.string.placeholder_error_title),
        body = msg ?: getString(R.string.placeholder_error_label)
    ).show()
}

inline fun <reified T : Activity> Context.intentFor(vararg args: Pair<String, Any?>): Intent {
    val intent = Intent(this, T::class.java)
    args.forEach { (key, value) ->
        // If you need anything else just add another type.
        when (value) {
            is Int -> intent.putExtra(key, value)
            is Short -> intent.putExtra(key, value)
            is Long -> intent.putExtra(key, value)
            is Float -> intent.putExtra(key, value)
            is Double -> intent.putExtra(key, value)
            is String -> intent.putExtra(key, value)
            is Boolean -> intent.putExtra(key, value)
            is Byte -> intent.putExtra(key, value)
            is Char -> intent.putExtra(key, value)
            is Parcelable -> intent.putExtra(key, value)
            is Serializable -> intent.putExtra(key, value)
        }
    }
    return intent
}

fun Intent.withFlags(vararg flags: Int): Intent {
    return this.apply {
        flags.forEach {
            addFlags(it)
        }
    }
}

fun Context.openVideoPlayer(video: String?) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.parse(video), "video/*")
    startActivity(intent)
}

fun Context.externalShare(content: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, content)
        type = "text/plain"
    }
    startActivity(sendIntent)
}

fun Context.openLocationInExternalApps(
    latitude: Double,
    longitude: Double,
    title: String = "Selecione um aplicativo"
) {
    val url = "waze://?ll=$latitude,$longitude&navigate=yes"
    val intentWaze = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intentWaze.setPackage("com.waze")

    val uriGoogle = "google.navigation:q=$latitude,$longitude"
    val intentGoogleNav = Intent(Intent.ACTION_VIEW, Uri.parse(uriGoogle))
    intentGoogleNav.setPackage("com.google.android.apps.maps")

    val chooserIntent = Intent.createChooser(intentGoogleNav, title)
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(intentWaze))

    startActivity(chooserIntent)
}

fun Context.playRingtone(type: Int = RingtoneManager.TYPE_NOTIFICATION) {
    val defaultUri = RingtoneManager.getDefaultUri(type)
    val mp = MediaPlayer.create(applicationContext, defaultUri)
    mp.start()
}

fun Context.getDeviceVolumePercentage(streamType: Int = AudioManager.STREAM_MUSIC): Float {
    val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val currentVolume = audioManager.getStreamVolume(streamType)
    val maxVolume = audioManager.getStreamMaxVolume(streamType)
    return (1f * currentVolume) / maxVolume
}

fun Context.createAppSettingsIntent() = Intent().apply {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    data = Uri.fromParts("package", packageName, null)
}