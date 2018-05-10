package com.example.revern.rxpmbase.ui.base.rc_view

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseRcvHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

}
