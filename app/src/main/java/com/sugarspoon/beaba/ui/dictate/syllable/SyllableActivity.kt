package com.sugarspoon.beaba.ui.dictate.syllable

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.data.model.ItemSyllable
import com.sugarspoon.beaba.data.repositories.SyllableRepository
import com.sugarspoon.beaba.utils.extensions.intentFor
import kotlinx.android.synthetic.main.activity_syllable.*
import kotlinx.coroutines.flow.collect

class SyllableActivity : BaseActivity() {

    private val factory = SyllableViewModel.Factory(SyllableRepository())
    private val viewModel by viewModels<SyllableViewModel> { factory }

    private val adapter by lazy {
        SyllableAdapter(
            onItemClicked = object : SyllableAdapter.Listener {
                override fun onItemClicked(adapterPosition: Int) {
                    viewModel.setNextItem(adapterPosition)
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syllable)
        setToolbar(getString(R.string.syllable_toolbar), true)
        setupUi()
        setupObserver()
    }

    private fun setupUi() {
        syllableItemsRv.layoutManager = LinearLayoutManager(this )
        syllableItemsRv.adapter = adapter
    }

    private fun setupObserver() {
        this.lifecycleScope.launchWhenStarted {
            viewModel.items.collect {
                adapter.setItems(it.toMutableList())
            }
        }
    }

    companion object {
        fun intent(context: Context) = context.intentFor<SyllableActivity>()
    }
}