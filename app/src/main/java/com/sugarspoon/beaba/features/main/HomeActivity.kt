package com.sugarspoon.beaba.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.data.HomeRepository
import com.sugarspoon.beaba.data.HomeRepository.Companion.DICTATE
import com.sugarspoon.beaba.data.HomeRepository.Companion.MATHS
import com.sugarspoon.beaba.data.HomeRepository.Companion.PAINT
import com.sugarspoon.beaba.extensions.startActivitySlideTransition
import com.sugarspoon.beaba.features.dictate.vowel.DictateActivity
import com.sugarspoon.beaba.features.math.MathActivity
import com.sugarspoon.beaba.features.paint.PaintActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class HomeActivity : BaseActivity() {

    private val factory = HomeViewModel.Factory(HomeRepository())
    private val viewModel by viewModels<HomeViewModel> { factory }

    private val adapter by lazy {
        HomeAdapter(
            onItemClicked = object : HomeAdapter.Listener {
                override fun onItemClicked(item: ItemHome.Subjects) {
                    when(item.router) {
                        DICTATE -> startActivitySlideTransition(
                            DictateActivity.intent(this@HomeActivity))
                        MATHS -> startActivitySlideTransition(
                            MathActivity.intent(this@HomeActivity))
                        PAINT -> startActivitySlideTransition(
                            PaintActivity.intent(this@HomeActivity))
                    }
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar(getString(R.string.toolbar_title, "Sophia"), false)
        setupUi()
        setupObserver()
    }

    private fun setupUi() {
        homeItemsRv.layoutManager = LinearLayoutManager(this)
        homeItemsRv.adapter = adapter
    }

    private fun setupObserver() {
        this.lifecycleScope.launchWhenStarted {
            viewModel.items.collect {
                adapter.list = it.toMutableList()
            }
        }
    }
}