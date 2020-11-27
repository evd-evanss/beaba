package com.sugarspoon.beaba.ui.math

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.data.model.ItemCount
import com.sugarspoon.beaba.data.repositories.MathsRepository
import com.sugarspoon.beaba.utils.extensions.intentFor
import kotlinx.android.synthetic.main.activity_math.*
import kotlinx.coroutines.flow.collect

class MathActivity : BaseActivity() {

    private val factory = MathViewModel.Factory(MathsRepository())
    private val viewModel by viewModels<MathViewModel> { factory }

    private val adapter by lazy {
        CounterAdapter(
            onItemClicked = object : CounterAdapter.Listener {
                override fun onItemClicked(item: ItemCount) {

                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)
        setToolbar(getString(R.string.maths_toolbar), true)
        setupUi()
        setupObserver()
    }

    private fun setupUi() {
        counterItemsRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL )
        counterItemsRv.adapter = adapter
    }

    private fun setupObserver() {
        this.lifecycleScope.launchWhenStarted {
            viewModel.items.collect {
                adapter.list = it.toMutableList()
            }
        }
    }

    companion object {
        fun intent(context: Context) = context.intentFor<MathActivity>()
    }
}