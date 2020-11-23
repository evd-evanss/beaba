package com.sugarspoon.beaba.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.sugarspoon.beaba.utils.extensions.setVisible
import kotlinx.android.synthetic.main.layout_toolbar.view.*

open class BaseActivity : AppCompatActivity() {

    var view: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        view = (window.decorView.rootView as? ViewGroup)
    }

    private fun setupListener() = view?.run {
        toolbarBackIv.setOnClickListener {
            onBackPressed()
        }
    }

    fun setToolbar(title: String, displayHomeAsUpEnabled: Boolean = false) = view?.run {
        toolbarTitleTv.text = title
        toolbarBackIv.setVisible(displayHomeAsUpEnabled)
        setupListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setDisplayHomeAsUpEnabled(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }

    fun setOrientationLandscape() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    fun setOrientationPortrait() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
