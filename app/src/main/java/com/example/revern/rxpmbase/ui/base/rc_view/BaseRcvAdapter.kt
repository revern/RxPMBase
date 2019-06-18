package com.example.revern.rxpmbase.ui.base.rc_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Function

open class BaseRcvAdapter<T, VH : BaseRcvHolder<T>>(
        private var items: MutableList<T>,
        private var holderCreator: Function<ViewGroup, VH>,
        private var onRcvItemClickFunc: ((T) -> Unit)? = null)
    : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder = holderCreator.apply(parent)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION)
                onRcvItemClickFunc?.invoke(items[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

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
