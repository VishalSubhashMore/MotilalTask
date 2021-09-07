package com.example.motilaltask.api

import com.example.motilaltask.model.RepositoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): Call<RepositoryList>?
}