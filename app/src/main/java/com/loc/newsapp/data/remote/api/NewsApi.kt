package com.loc.newsapp.data.remote.api

import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.util.Consts.NEWS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("page")
        page: Int,
        @Query("source")
        string: String,
        @Query("apiKey")
        apiKey: String = NEWS_API_KEY
    ): NewsResponse

}