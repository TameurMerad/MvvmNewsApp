package com.loc.newsapp.ui.theme.Presentation.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import com.loc.newsapp.ui.theme.BlueGray
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.loc.newsapp.ui.theme.Presentation.Dimens
import com.loc.newsapp.ui.theme.Presentation.Dimens.IndicatorSize

@Composable

fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){

    Column (modifier) {
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        Text(
            text = page.title,
            Modifier.padding(horizontal = Dimens.MediumPAdding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id =  R.color.display_small)
        )
        Text(
            text = page.title,
            Modifier.padding(horizontal = Dimens.MediumPAdding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id =  R.color.text_medium)
        )
    }

}
@Preview (showBackground = true)

@Composable
fun Prev (){
    NewsAppTheme {
        OnBoardingPage(page = pages[0])
    }

}



@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagesSize: Int,
    selectedPage: Int,
    selectedColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    unselectedColor: androidx.compose.ui.graphics.Color = BlueGray,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(times = pagesSize) { page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}