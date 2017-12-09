package com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.User
import kotlinx.android.synthetic.main.user_list_item_layout.view.*
import java.lang.Exception


class UserListViewHolder(view: View, itemClickListener: OnItemPositionClickListener): BaseViewHolder<User>(view){

    init {
        itemView.setOnClickListener { itemClickListener.onItemClicked(adapterPosition) }
    }

    override fun bind(model: User) {
        with(itemView){
            Glide.with(context)
                    .load(model.avatarUrl)
                    .listener(object :  RequestListener<String, GlideDrawable> {
                        override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                            Log.e("RequestListenerTag", e.toString())
                            return false
                        }

                        override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                            Log.i("ResourceReady", "ResourceReady")
                            return false
                        }

                    } )
                    .into(avatarImage)
            loginText.text = model.login
        }
    }
}