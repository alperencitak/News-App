package com.alperencitak.newsapp.data.remote

import com.alperencitak.newsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.alperencitak.newsapp.util.Constants.API_KEY

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}