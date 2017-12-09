package com.example.radmushroom.cloudmade_testtask_bugay_sergey.network

import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.Repo
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.SearchResponse
import com.example.radmushroom.cloudmade_testtask_bugay_sergey.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("search/users")
    fun searchUsers(@Query("q") query: String,
                    @Query("page") page: Int): Call<SearchResponse>

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user:String,
                     @Query("page") page: Int) : Call<List<Repo>>

    @GET("users/{user}")
    fun getUserInfo(@Path("user") user: String) : Call<User>
}