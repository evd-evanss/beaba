package com.sugarspoon.beaba.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.beaba.R
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(private val onItemClicked: Listener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var onSubjectClicked: ((ItemHome.Subjects) -> Unit)? = null

    var list: MutableList<ItemHome.Subjects> = mutableListOf()

    fun setCardList(cardList: List<ItemHome.Subjects>) {
        list.addAll(cardList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is SubjectViewHolder) {
            holder.bind(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home, parent, false)
        )
    }

    override fun getItemCount() = list.size

    inner class SubjectViewHolder(itemView: View) : BaseViewHolder<ItemHome.Subjects>(itemView) {
        override fun bind(item: ItemHome.Subjects) {
            itemView.apply {
                item.run {
                    background.setTint(ContextCompat.getColor(context, item.color))
                    itemHomeIconIv.setImageResource(item.icon)
                    itemHomeTitleTv.text = context.getString(item.text)
                    setOnClickListener {
                        onItemClicked.onItemClicked(item)
                    }
                }
            }
        }
    }

    interface Listener {
        fun onItemClicked(item: ItemHome.Subjects)
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
