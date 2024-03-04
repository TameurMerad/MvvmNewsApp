package com.loc.newsapp.data.remote.api

import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.util.Consts
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiTest {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country")
        string: String,
        @Query("apiKey")
        apiKey: String = Consts.NEWS_API_KEY
    ): NewsResponse

}