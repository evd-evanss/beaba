package com.sugarspoon.beaba.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}

fun EditText.afterTextChanged(onTextChanged: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onTextChanged(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun EditText.addTextMask(mask: String) {
    addTextChangedListener(MaskedText.applyMasked(mask, this))
}

fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<in RecyclerView.ViewHolder>,
    layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this.context),
    decoration: RecyclerView.ItemDecoration? = null,
    hasFixedSize: Boolean = true) {
    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(hasFixedSize)
    decoration?.let { this.addItemDecoration(it) }
}


