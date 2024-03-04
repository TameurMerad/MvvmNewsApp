package com.loc.newsapp.Presentation.commun

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.Presentation.Dimens
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.Presentation.commun.*


@Composable
fun ArticlesList(
     modifier: Modifier = Modifier,
     articles : LazyPagingItems<Article>,
     onArticleClick: (Article) -> Unit
    ) {
    val result = handlePagingResult(articles)
    if (result){
        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
            contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding)
        ){
            items(articles.itemCount){ it ->
                articles[it]?.let {
                    ArticleCard(
                        article = it,
                        onClick = { onArticleClick(it) }
                    )
                }
            }
        }
    }




}










@Composable
fun ArticlesListTest(
    modifier: Modifier = Modifier,
    articles : List<Article>,
    onArticleClick: (Article) -> Unit
) {


        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
            contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding)
        ){
            items(articles.size){ it ->
                articles[it]?.let {
                    ArticleCard(
                        article = it,
                        onClick = { onArticleClick(it) }
                    )
                }
            }
        }
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
 fun ShimmerEffect() {
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



