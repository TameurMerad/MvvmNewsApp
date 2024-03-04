package com.loc.newsapp.Presentation.navgraph

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.Presentation.home.HomeScreenn
import com.loc.newsapp.Presentation.home.HomeViewModel
import com.loc.newsapp.Presentation.onBoarding.OnBoardingScreen
import com.loc.newsapp.Presentation.onBoarding.OnBoardingVM
import com.loc.newsapp.data.remote.api.NewsApiTest
import com.loc.newsapp.util.Consts
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Composable
fun NavGraph(
    startDestination:String
) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {

            composable(Route.OnBoardingScreen.route){
                val viewModel : OnBoardingVM = hiltViewModel()
                OnBoardingScreen(viewModel= viewModel)
            }

        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsScreen.route
        ) {

            composable(Route.NewsScreen.route){
//                Text(text = "News Screen",Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.Red))
//                HomeScreen()
                val viewModel :HomeViewModel = hiltViewModel()
                val newsState by viewModel.newsState.collectAsState()



                HomeScreenn(newsState, navigate = {})

//                    val articles = viewmodel.news.collectAsLazyPagingItems()
//                    HomeScreen(articles = articles, navigate = { })








            }
        }

    }

}