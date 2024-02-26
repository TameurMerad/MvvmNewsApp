package com.loc.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loc.newsapp.Presentation.navgraph.NavGraph
import com.loc.newsapp.Presentation.navgraph.Route
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.loc.newsapp.Presentation.onBoarding.OnBoardingScreen
import com.loc.newsapp.Presentation.onBoarding.OnBoardingVM
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject



@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        Log.d("testdzb", "hkak brk")
        lifecycleScope.launch {
            appEntryUseCases.readEntry().collect {
                Log.d("testdzb", it.toString())
            }
        }


        installSplashScreen()
        setContent {
            NewsAppTheme {
                val isDarkTheme = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isDarkTheme
                    )
                }

                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
//                        val viewModel = OnBoardingVM(appEntryUseCases)
//                        OnBoardingScreen(viewModel= viewModel)
                        val startDestination = viewModel.startDestination
                        NavGraph(startDestination)


                    }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
//            OnBoardingScreen()
        }
    }
}
