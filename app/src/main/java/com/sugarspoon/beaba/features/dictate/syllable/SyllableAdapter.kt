package com.sugarspoon.beaba.features.dictate.syllable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.ItemSyllable
import kotlinx.android.synthetic.main.item_syllable.view.*

class SyllableAdapter(private val onItemClicked: Listener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var list: MutableList<ItemSyllable> = mutableListOf()

    fun setCardList(cardList: List<ItemSyllable>) {
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
                    setOnClickListener {
                        onItemClicked.onItemClicked(item)
                    }
                }
            }
        }
    }

    interface Listener {
        fun onItemClicked(item: ItemSyllable)
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
