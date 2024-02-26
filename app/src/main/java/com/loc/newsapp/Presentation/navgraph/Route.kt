package com.loc.newsapp.Presentation.navgraph

sealed class Route (val route: String) {

    object OnBoardingScreen: Route("onboarding")
    object HomeScreen: Route("home")
    object SearchScreen: Route("settings")
    object BookmarksScreen: Route("bookmarks")
    object NewsScreen: Route("news")
    object DetailsScreen: Route("details")
    object AppStartNavigation: Route("appStart")
    object NewsNavigation: Route("newsNavigation")
    object NewsNavigationScreen: Route("newsNavigationScreen")

}