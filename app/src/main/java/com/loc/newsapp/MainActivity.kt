package com.loc.newsapp

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loc.newsapp.Presentation.navgraph.NavGraph
import com.loc.newsapp.data.remote.api.NewsApi
import com.loc.newsapp.data.remote.dto.NewsResponse
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.util.Consts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.inject.Inject



@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val device_id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        WindowCompat.setDecorFitsSystemWindows(window, false)
//        Log.d("testdzb", device_id)
//        lifecycleScope.launch {
//            appEntryUseCases.readEntry().collect {
//                Log.d("testdzb", it.toString())
//            }
//        }
//        lifecycleScope.launch {
//            val apiClient = Retrofit.Builder()
//                .baseUrl(Consts.NEWS_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(
//                    OkHttpClient.Builder()
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .writeTimeout(30, TimeUnit.SECONDS)
//                    .build())
//                .build()
//                .create(NewsApiTest::class.java)
//            try {
//                // Your Retrofit API call
//                val response = apiClient.getNews("us", Consts.NEWS_API_KEY)
//                Log.e("myresponsedzb", response.toString())
//
//                // Process the successful response
//
//            } catch (e: HttpException) {
//                // Handle HTTP errors
//                val errorBody = e.response()?.errorBody()?.string()
//                Log.e("myresponsedzb", "HTTP error: $errorBody")
//            } catch (e: Exception) {
//                // Handle other exceptions
//                Log.e("myresponsedzb", e.toString())
//            }
//

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

                        val sccocop = rememberCoroutineScope()



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





