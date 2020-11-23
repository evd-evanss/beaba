package com.sugarspoon.beaba.utils.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import java.util.*

fun Context.getColorRes(idRes: Int): Int {
    return ContextCompat.getColor(this, idRes)
}

fun Context.getDrawableCompat(@DrawableRes drawableResId: Int): Drawable? {
    return AppCompatResources.getDrawable(this, drawableResId)
}

@Deprecated("Use makeCall() from anko instead")
fun Context.callPhone(phone: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        return false
    }
    return true
}

fun Context.navigateIntent(address: String = "", latitude: String = "0", longitude: String = "0") {
    val gmmIntentUri = "geo:$latitude,$longitude?q=$address"
    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gmmIntentUri))
    startActivity(mapIntent)
}

fun Context.notImplementedFeature(msg: String? = null) {
    val fullMsg = if (msg != null) {
        "Função não implementada - " + msg
    } else {
        "Função não implementada"
    }
    this.showToast(fullMsg)
}

fun Context.newColorStateList(color: Int): ColorStateList {
    return ColorStateList(
        arrayOf(
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_focused),
            intArrayOf(android.R.attr.state_focused, android.R.attr.state_pressed)
        ),
        intArrayOf(color, color, color)
    )
}

fun rand(from: Int, to: Int): Int {
    return Random().nextInt(to - from) + from
}

/**
 * Calls the specified function [block] and if it does not crash returns its result, else returns null.
 */
fun <T> tryOrNull(block: () -> T): T? {
    return try {
        block.invoke()
    } catch (e: Exception) {
        null
    }
}

fun Int.toStateList() = ColorStateList.valueOf(this)
