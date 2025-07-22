package com.example.newsapp.Data.remote.dto

import com.example.newsapp.utill.Constants.API_Key
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String = API_Key
    ):NewsResponse

    @GET("everything")

    suspend fun searchNews(
        @Query("q") searchQuery:String,
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String = API_Key
    ):NewsResponse
}