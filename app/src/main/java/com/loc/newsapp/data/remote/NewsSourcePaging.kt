package com.loc.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.data.remote.api.NewsApi
import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.util.Consts

class NewsSourcePaging(
    val newsApi: NewsApi,
    val sources: String,

):PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    private var totalNewsCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews( page, sources)
            Log.d("myresponsedzbtest", newsResponse.toString())
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalNewsCount==newsResponse.totalResults) null else page + 1
            )

        }catch (e:Exception){
            Log.d("myresponsedzbtest", e.toString())

            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}