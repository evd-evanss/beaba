package com.sugarspoon.beaba.ui.math

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.beaba.R
import com.sugarspoon.beaba.data.model.ItemCount
import com.sugarspoon.beaba.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_count.view.*

class CounterAdapter(private val onItemClicked: Listener) :
    RecyclerView.Adapter<CounterAdapter.MathViewHolder>() {

    var list: MutableList<ItemCount> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MathViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_count, parent, false)

        return MathViewHolder(view)
    }

    override fun onBindViewHolder(holder: MathViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class MathViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ItemCount) {
            itemView.apply {
                item.run {
                    itemNumberTv.text = item.number.toString()
                    setOnClickListener {
                        onItemClicked.onItemClicked(item)
                    }
                    itemCountFingerLeft.setVisible(itemCountFingerLeft != null)
                    item.fingerLeft?.run {
                        itemCountFingerLeft.setImageDrawable(
                            ContextCompat.getDrawable(context, this)
                        )
                    }
                    itemCountFingerRight.setVisible(itemCountFingerLeft != null)
                    item.fingerRight?.run {
                        itemCountFingerRight.setImageDrawable(
                            ContextCompat.getDrawable(context, this)
                        )
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
