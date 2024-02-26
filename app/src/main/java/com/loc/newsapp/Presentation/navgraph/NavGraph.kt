package com.loc.newsapp.Presentation.navgraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.loc.newsapp.Presentation.onBoarding.OnBoardingScreen
import com.loc.newsapp.Presentation.onBoarding.OnBoardingVM
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import javax.inject.Inject

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
                Text(text = "News Screen",Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.Red))
            }
        }

    }

}