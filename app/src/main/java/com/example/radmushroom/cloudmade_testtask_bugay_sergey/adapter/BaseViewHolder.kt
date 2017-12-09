package com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in Model>(itemView : View) : RecyclerView.ViewHolder(itemView) {

    internal abstract fun bind(model: Model)

}