package com.sugarspoon.beaba.ui.main

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.base.BaseActivity
import com.sugarspoon.beaba.data.model.ItemHome
import com.sugarspoon.beaba.data.repositories.HomeRepository
import com.sugarspoon.beaba.data.repositories.HomeRepository.Companion.DICTATE
import com.sugarspoon.beaba.data.repositories.HomeRepository.Companion.MATHS
import com.sugarspoon.beaba.data.repositories.HomeRepository.Companion.PAINT
import com.sugarspoon.beaba.ui.dictate.vowel.DictateActivity
import com.sugarspoon.beaba.ui.math.MathActivity
import com.sugarspoon.beaba.ui.paint.PaintActivity
import com.sugarspoon.beaba.utils.extensions.startActivitySlideTransition
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

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar(getString(R.string.toolbar_title, ""), false)
        setupUi()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer = MediaPlayer.create(this, R.raw.theme)
        mediaPlayer.start()
        setupReplay()
    }

    private fun setupReplay() {
        mediaPlayer.setOnCompletionListener {
            it.start()
        }
    }

    private fun setupUi() {
        homeItemsRv.layoutManager = LinearLayoutManager(this)
        homeItemsRv.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    private fun setupObserver() {
        this.lifecycleScope.launchWhenStarted {
            viewModel.items.collect {
                adapter.list = it.toMutableList()
            }
        }
    }
}