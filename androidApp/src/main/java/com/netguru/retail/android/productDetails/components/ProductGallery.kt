package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.netguru.retail.android.shared.FadingAsyncImage
import com.netguru.retail.android.theme.dimens

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductGallery(
    modifier: Modifier,
    gallery: List<String>
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            count = gallery.size
        ) { page ->
            FadingAsyncImage(
                imageUrl = gallery[page],
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (pagerState.pageCount > 1) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.Black.copy(alpha = 0.3f),
                indicatorShape = CircleShape,
                modifier = Modifier
                    .padding(MaterialTheme.dimens.grid_1)
                    .padding(bottom = MaterialTheme.dimens.grid_2)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
