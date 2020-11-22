package com.sugarspoon.beaba.base

import android.view.MenuItem

abstract class BaseFragment : androidx.fragment.app.Fragment() {

    protected val TAG =
        if (javaClass.enclosingClass != null) javaClass.enclosingClass?.simpleName else javaClass.simpleName

    fun setOrientationLandscape() {
        (activity as BaseActivity).setOrientationLandscape()
    }

    fun setOrientationPortrait() {
        (activity as BaseActivity).setOrientationPortrait()
    }

    //MENU METHODS
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
