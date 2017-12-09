package com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.R
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.Repo

/**
 * Created by RadMushroom on 07.12.2017.
 */
class ReposListAdapter : BaseRecyclerAdapter<Repo>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Repo> {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.repos_list_item_layout, parent, false)
        return ReposListViewHolder(view)
    }
}