package com.netguru.retail.android.userSingleImage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netguru.retail.android.shared.FadingAsyncImage
import com.netguru.retail.android.shared.RetailTopBar
import com.netguru.retail.android.theme.backgroundVariant
import com.netguru.retail.android.theme.dimens

@Composable
fun UserSingleImageScreen(
    viewModel: UserSingleImageViewModel = viewModel()
) {
    BackHandler(enabled = true) {
        viewModel.handleBack()
    }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            RetailTopBar(
                onClickNav = { viewModel.handleBack() },
                modifier = Modifier.background(
                    color = MaterialTheme.colors.backgroundVariant
                )
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.backgroundVariant)
                .padding(scaffoldPadding)
                .padding(
                    horizontal = MaterialTheme.dimens.grid_3,
                    vertical = MaterialTheme.dimens.grid_2
                )
        ) {
            Text(
                text = viewModel.imageTitle,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(bottom = MaterialTheme.dimens.grid_3)
            )
            FadingAsyncImage(
                modifier = Modifier.clip(MaterialTheme.shapes.medium),
                imageUrl = viewModel.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
