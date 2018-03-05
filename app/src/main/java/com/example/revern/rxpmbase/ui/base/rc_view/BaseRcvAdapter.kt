package com.helper.revern.base.rc_view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.reactivex.functions.Function

open class BaseRcvAdapter<T, VH : BaseRcvHolder<T>>(
        private var items: MutableList<T>,
        private var holderCreator: Function<ViewGroup, VH>,
        private var onRcvItemClickListener: OnRcvItemClickListener<T>? = null)
    : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        val holder = holderCreator.apply(parent!!)
        holder.itemView.setOnClickListener { view ->
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION)
                onRcvItemClickListener?.onItemClick(view, getItems()[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItems()[position])

    override fun getItemCount() = items.size

    fun getItems(): List<T> {
        return items
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun remove(item: T) {
        remove(items.indexOf(item))
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}