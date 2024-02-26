package com.loc.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.loc.newsapp.Presentation.onBoarding.OnBoardingScreen
import com.loc.newsapp.Presentation.onBoarding.OnBoardingVM
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d("zbiii", "it.toString()")
        lifecycleScope.launch {
            appEntryUseCases.readEntry().collect{
                Log.d("test", it.toString())
            }
        }
        installSplashScreen()
        setContent {
            NewsAppTheme {
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val viewModel = OnBoardingVM(appEntryUseCases)
                        OnBoardingScreen(viewModel= viewModel)

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
