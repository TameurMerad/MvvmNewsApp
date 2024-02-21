package com.loc.newsapp.ui.theme.Presentation.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {



    Column(Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Lessgo!")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { page ->
            pages[page]
        }


}
}