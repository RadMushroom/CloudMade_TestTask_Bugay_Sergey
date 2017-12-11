package com.example.radmushroom.cloudmade_testtask_bugay_sergey


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter.OnItemPositionClickListener
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.adapter.UserListAdapter
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.SearchResponse
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.User
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.network.ApiService
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(), OnItemPositionClickListener {

    private var searchQuery: String = ""
    private lateinit var responseList: MutableList<User>
    private var userListAdapter = UserListAdapter(this)
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)

    }

    override fun onItemClicked(position: Int) {
        val username: String? = userListAdapter.getItem(position)?.login
        val i = Intent(activity, UserDetailActivity::class.java)
        i.putExtra("username", username)
        startActivity(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersListRecyclerView.adapter = userListAdapter
        usersListRecyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(usersListRecyclerView.layoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                searchUsers(searchQuery, page)
            }
        })
        searchButton.setOnClickListener {
                getUsersList()
            }
        }

    private fun getUsersList() {
        searchQuery = searchEditText.text.toString().trim()
        searchEditText.hideKeyboard()
        searchEditText.clearFocus()
        searchUsers(searchQuery, 1)
    }

    private fun searchUsers(searchQuery: String, page: Int) {
        if (searchQuery.isNotEmpty()) {
            ApiService.searchUsers(searchQuery, page, object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                    activity.runOnUiThread { Toast.makeText(activity, "No Response!", Toast.LENGTH_SHORT).show() }
                }

                override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                    activity.runOnUiThread {
                        responseList = response?.body()?.items ?: arrayListOf()
                        Toast.makeText(activity, "current: ${userListAdapter.itemCount} new : ${responseList.size}", Toast.LENGTH_SHORT).show()
                        if (page == 1) {
                            userListAdapter.setList(responseList)
                        } else {
                                userListAdapter.addItems(responseList)
                        }
                    }
                }
            })
        } else {
            Toast.makeText(activity, "SearchBox can't be empty!", Toast.LENGTH_SHORT).show()
        }
    }
}
