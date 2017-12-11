package com.example.radmushroom.cloudmade_testtask_bugay_sergey

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter.ReposListAdapter
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.Repo
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.User
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.network.ApiService
import kotlinx.android.synthetic.main.fragment_user_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UserDetailsFragment : Fragment() {

    private val reposListAdapter = ReposListAdapter()
    private var username: String = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reposListRecyclerView.adapter = reposListAdapter
        reposListRecyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(reposListRecyclerView.layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                getReposList(username, page)
            }

        })
        val extra: Bundle = activity.intent.extras
        username = extra.get("username").toString()
        getUserInfo(username)
        getReposList(username, 1)

    }

    private fun getUserInfo(username: String) {
        ApiService.getUserInfo(username, object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(activity, "Can't get user info!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                activity.runOnUiThread {
                    val user = User(response?.body()?.login ?: String(), response?.body()?.avatarUrl ?: String(),
                            response?.body()?.name ?: String(), response?.body()?.company ?: String(),
                            response?.body()?.blog ?: String(), response?.body()?.location ?: String(),
                            response?.body()?.email ?: String(), response?.body()?.bio ?: String())
                    loginDetail.text = user.login
                    nameDetail.text = user.name
                    companyText.text = user.company
                    blogDetail.text = user.blog
                    locationDetail.text = user.location
                    emailDetail.text = user.email
                    bioDetail.text = user.bio
                    Glide.with(activity)
                            .load(user.avatarUrl)
                            .listener(object : RequestListener<String, GlideDrawable> {
                                override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                                    Log.e("RequestListenerTag", e.toString())
                                    return false
                                }

                                override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                                    Log.i("ResourceReady", "ResourceReady")
                                    return false
                                }

                            })
                            .into(userAvatar)

                }
            }
        })
    }


    private fun getReposList(username: String, page: Int) {
        ApiService.getUserRepos(username, page, object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                activity.runOnUiThread {
                    val reposList = response?.body() ?: arrayListOf()
                    Toast.makeText(activity, "current: ${reposListAdapter.itemCount} new: ${reposList.size}",
                            Toast.LENGTH_SHORT).show()
                        reposListAdapter.addItems(reposList)
                    }
                }
            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                Toast.makeText(activity, "can't get user repos!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}// Required empty public constructor
