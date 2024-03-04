package com.loc.newsapp.Presentation.home

import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.data.remote.api.NewsApiTest
import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.util.Consts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class NewsUiState(val articles: List<Article>, val isLoading: Boolean = false, val errorMessage: String? = null)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
) : ViewModel() {
    val news = newsUseCases.getNews(listOf("us")).cachedIn(viewModelScope)


    private val _newsState = MutableStateFlow(NewsUiState(emptyList(), isLoading = true))

    val newsState: StateFlow<NewsUiState> = _newsState
    init {
        fetchData()
    }

    private fun fetchData() {
        val apiClient = Retrofit.Builder()
            .baseUrl(Consts.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(NewsApiTest::class.java)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiClient.getNews("us")
                _newsState.value = NewsUiState(response.articles)
            } catch (e: Exception) {
                _newsState.value = NewsUiState(emptyList(), errorMessage = "Error fetching news: ${e.message}")
                Log.e("myresponsedzb", e.toString())
            }
        }
    }



}