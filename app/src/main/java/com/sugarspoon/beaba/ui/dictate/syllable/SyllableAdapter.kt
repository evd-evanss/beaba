package com.sugarspoon.beaba.ui.dictate.syllable

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.ItemSyllable
import com.sugarspoon.beaba.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_syllable.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class SyllableAdapter(private val onItemClicked: Listener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var list: MutableList<ItemSyllable> = mutableListOf()
    private var blink = false

    fun setItems(cardList: List<ItemSyllable>) {
        list.clear()
        list.addAll(cardList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_syllable, parent, false)
        )
    }

    override fun getItemCount() = list.size

    inner class ItemViewHolder(itemView: View) : BaseViewHolder<ItemSyllable>(itemView) {
        override fun bind(item: ItemSyllable) {
            itemView.apply {
                item.run {
                    itemSyllableTv.text = item.syllable
                    itemSyllableTv.setVisible(item.selected)
                    setOnClickListener {
                        onItemClicked.onItemClicked(adapterPosition)
                    }
                }
                //startAnimation(context)
            }
        }

        private var job = Job()
        private val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        private var viewBlink: View? = null

        private fun startAnimation(context: Context) {
            if(job.isActive) job.cancel()
            job = Job()
            val coroutineScope = CoroutineScope(Dispatchers.IO + job + handler)
            coroutineScope.launch {
                while (isActive) {
                    turnOn(context = context)
                    delay(300)
                    turnOff(context)
                    delay(300)
                    Log.d("COROUTINE_", "isActive")
                }
            }
        }

        private fun turnOn(context: Context) {
            CoroutineScope(Main).launch {
                viewBlink?.background?.setTint(ContextCompat.getColor(context, R.color.green))
            }
        }

        private fun turnOff(context: Context) {
            CoroutineScope(Main).launch {
                viewBlink?.background?.setTint(ContextCompat.getColor(context, R.color.color_gray))
            }
        }
    }



    interface Listener {
        fun onItemClicked(adapterPosition: Int)
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
