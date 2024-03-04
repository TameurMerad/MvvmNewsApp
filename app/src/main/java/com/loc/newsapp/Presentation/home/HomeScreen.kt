package com.loc.newsapp.Presentation.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.Presentation.Dimens.MediumPadding1
import com.loc.newsapp.Presentation.commun.ArticlesList
import com.loc.newsapp.Presentation.commun.*
import com.loc.newsapp.Presentation.navgraph.Route
import com.loc.newsapp.R
import com.loc.newsapp.data.remote.api.NewsApiTest
import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.util.Consts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun HomeScreen(articles :LazyPagingItems<Article>, navigate:(String)->Unit) {
//
//    val titles by remember {
//        derivedStateOf {
//            if (articles.itemCount > 10) {
//                articles.itemSnapshotList.items
//                    .slice(IntRange(start = 0, endInclusive = 9))
//                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
//            } else {
//                ""
//            }
//        }
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = MediumPadding1)
//            .statusBarsPadding()
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_logo),
//            contentDescription = null,
//            modifier = Modifier
//                .width(150.dp)
//                .height(30.dp)
//                .padding(horizontal = MediumPadding1)
//        )
//        Spacer(modifier = Modifier.height(MediumPadding1))
//
//        SearchBar(
//            modifier = Modifier
//                .padding(horizontal = MediumPadding1)
//                .fillMaxWidth(),
//            text = "",
//            readOnly = true,
//            onValueChange = {},
//            onSearch = {},
//            onClick = {
//                navigate(Route.SearchScreen.route)
//            }
//        )
//
//        Spacer(modifier = Modifier.height(MediumPadding1))
//
//        Text(
//            text = titles, modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = MediumPadding1)
//                .basicMarquee(), fontSize = 12.sp,
//            color = colorResource(id = R.color.placeholder)
//        )
//
//        Spacer(modifier = Modifier.height(MediumPadding1))
//
//        ArticlesList(
//            Modifier.padding(horizontal = MediumPadding1)
//            ,articles = articles){
//            navigate(Route.DetailsScreen.route)
//        }
//
//
//
//    }
//
//
//
//}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenn(newsState : NewsUiState,navigate:(String)->Unit) {


//    val titles by remember {
//        derivedStateOf {
//            if (articles.size > 10) {
//                articles.itemSnapshotList.items
//                    .slice(IntRange(start = 0, endInclusive = 9))
//                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
//            } else {
//                ""
//            }
//        }
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigate(Route.SearchScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = "--------------------hfbvj---------------------------------------------------------------------------------------fkvjbn--------",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))


        if (newsState.isLoading) {
        // Display a loading indicator
        ShimmerEffect()
        } else if (newsState.errorMessage != null) {
        // Display an error message
        // You can show newsState.errorMessage to the user
        } else {
        // Display your content, for example, a list of articles
        ArticlesListTest(
            Modifier.padding(
                horizontal = MediumPadding1
            ),
            articles = newsState.articles
        ) {
            // Handle item click, for example, navigate to details screen
            navigate(Route.DetailsScreen.route)
        }
    }
    }
}









@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

    }
}



//Box(modifier = Modifier.fillMaxSize(),
//contentAlignment = Alignment.Center
//) {
//    NewsAppTheme(dynamicColor = false) {
//        ArticleCard(
//            article = Article(
//                author = "",
//                content = "",
//                description = "",
//                publishedAt = "2 hours",
//                source = Source(id = "", name = "BBC"),
//                title = "Her train broke down. Her phone died. And then she met her Saver in a",
//                url = "",
//                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
//            )
//        )
//    }
//
//}