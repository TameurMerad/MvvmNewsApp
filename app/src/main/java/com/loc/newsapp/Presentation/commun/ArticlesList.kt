package com.loc.newsapp.Presentation.commun

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.Presentation.Dimens
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.ArticleCardShimmerEffect
import com.loc.newsapp.presentation.common.EmptyScreen


@Composable
fun ArticlesList(
     modifier: Modifier = Modifier,
     Articles : LazyPagingItems<Article>,
     onArticleClick: (Article) -> Unit
    ) {





}


@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}



@Composable
private fun ShimmerEffect() {
    Column (verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1)){
        repeat(10){
            ArticleCardShimmerEffect()
        }
    }

}


@Composable
@Preview
fun ShimmerEffectPreview() {
    ShimmerEffect()
}



