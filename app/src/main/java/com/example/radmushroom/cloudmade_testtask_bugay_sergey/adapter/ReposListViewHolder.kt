package com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter

import android.view.View
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.Repo
import kotlinx.android.synthetic.main.repos_list_item_layout.view.*


class ReposListViewHolder(view: View) : BaseViewHolder<Repo>(view) {

    override fun bind(model: Repo) {
        with(itemView){
            idText.text = model.id
            repoNameText.text = model.name
            descriptionText.text = model.description
        }
    }
}