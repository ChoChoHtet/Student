package com.android.kotlin.student.baseviewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseVH<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data:T)

}