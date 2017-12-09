package com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.R
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.User

/**
 * Created by RadMushroom on 06.12.2017.
 */
class UserListAdapter(private var itemClickListener: OnItemPositionClickListener) : BaseRecyclerAdapter<User>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<User> {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.user_list_item_layout, parent, false)
        return UserListViewHolder(view, itemClickListener)
    }
}