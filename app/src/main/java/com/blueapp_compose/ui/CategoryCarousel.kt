package com.blueapp_compose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.util.lerp
import coil.compose.rememberAsyncImagePainter
import com.blueapp_compose.models.CategoryItems
import com.blueapp_compose.theme.Dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun CategoryCarousel(items: List<CategoryItems>, onPageCallChange: (Int) -> Unit = {}, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(
        initialPage = 0
    )

    LaunchedEffect(pagerState.currentPage) {
        onPageCallChange(pagerState.currentPage)
    }

    Column(modifier = modifier) {
        HorizontalPager(
            count = items.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = Dimens.dp_8),
            modifier = Modifier
                .height(Dimens.dp_200)
                .fillMaxWidth()
        ) { page ->
            Card(
                shape = RoundedCornerShape(Dimens.dp_16),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = Dimens.min_scale,
                            stop = Dimens.max_scale,
                            fraction = Dimens.alpha_max - pageOffset.coerceIn(Dimens.alpha_zero, Dimens.alpha_max)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = Dimens.alpha_min,
                            stop = Dimens.alpha_max,
                            fraction = Dimens.alpha_max - pageOffset.coerceIn(Dimens.alpha_zero, Dimens.alpha_max)
                        )
                    }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(items[page].categoryImage),
                    contentDescription = items[page].categoryName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(Dimens.dp_16)
        )
    }
}
