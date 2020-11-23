package com.sugarspoon.beaba.features.math

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.ItemCount
import kotlinx.android.synthetic.main.item_count.view.*

class CounterAdapter(private val onItemClicked: Listener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var list: MutableList<ItemCount> = mutableListOf()

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_count, parent, false)
        )
    }

    override fun getItemCount() = list.size

    inner class ItemViewHolder(itemView: View) : BaseViewHolder<ItemCount>(itemView) {
        override fun bind(item: ItemCount) {
            itemView.apply {
                item.run {
                    itemNumberTv.text = item.number.toString()
                    setOnClickListener {
                        onItemClicked.onItemClicked(item)
                    }
                }
            }
        }
    }

    interface Listener {
        fun onItemClicked(item: ItemCount)
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
