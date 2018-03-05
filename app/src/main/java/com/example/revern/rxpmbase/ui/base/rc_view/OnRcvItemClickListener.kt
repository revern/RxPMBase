package com.helper.revern.base.rc_view

import android.view.View

interface OnRcvItemClickListener<T> {
    fun onItemClick(view: View, item: T)
}