package com.example.radmushroom.cloudmade_testtask_bugay_sergey.network

import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.Repo
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.SearchResponse
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.User
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path


object ApiService {

    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    private var api: Api = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api::class.java)

    fun searchUsers(query: String, page: Int, callback: Callback<SearchResponse>) {
        api.searchUsers(query, page).enqueue(callback)
    }

    fun getUserRepos(user: String, page: Int, callback: Callback<List<Repo>>)  {
        api.getUserRepos(user, page).enqueue(callback)
    }

    fun getUserInfo(@Path("user") user: String, callback: Callback<User>){
        api.getUserInfo(user).enqueue(callback)
    }
}