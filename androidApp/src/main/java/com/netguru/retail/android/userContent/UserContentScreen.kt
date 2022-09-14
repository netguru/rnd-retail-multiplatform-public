package com.netguru.retail.android.userContent

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netguru.commonResources.MR
import com.netguru.commondomain.base.Loading
import com.netguru.retail.android.shared.*
import com.netguru.retail.android.theme.backgroundVariant
import com.netguru.retail.android.theme.dimens

@Composable
fun UserContentScreen(
    viewModel: UserContentViewModel = viewModel()
) {
    BackHandler(true) {
        viewModel.handleBack()
    }

    UserContentScaffold(
        isLoading = viewModel.loading is Loading.InProgress,
        onBackClick = viewModel::handleBack
    ) {
        Text(
            text = viewModel.productTitle,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(bottom = MaterialTheme.dimens.grid_2)
        )
        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Adaptive(minSize = 128.dp),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.grid_3),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.grid_2),
            content = {
                items(
                    viewModel.images,
                    key = { imageUrl -> imageUrl },
                    contentType = { it.javaClass }
                ) { userContentImageUrl ->
                    UserContentItem(
                        userContentImageUrl = userContentImageUrl,
                        onItemClick = { viewModel.handleUserContentImageClick(userContentImageUrl) }
                    )
                }
                item {
                    AddUserContentItem(
                        onItemClick = viewModel::handleAddUserContentClick
                    )
                }
            }
        )
    }
}

@Composable
private fun UserContentScaffold(
    isLoading: Boolean,
    onBackClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            RetailTopBar(
                onClickNav = onBackClick,
                modifier = Modifier.background(
                    color = MaterialTheme.colors.backgroundVariant
                )
            )
        }
    ) { scaffoldPadding ->
        LoadingAwareView(isLoading = isLoading) {
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
                content()
            }
        }
    }
}

@Composable
private fun UserContentItem(
    userContentImageUrl: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FadingAsyncImage(
        imageUrl = userContentImageUrl,
        contentDescription = null,
        modifier = modifier
            .clickable { onItemClick() }
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun AddUserContentItem(
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable { onItemClick() }
            .height(120.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(MaterialTheme.dimens.grid_1)
                .align(Alignment.Center)
        ) {
            SvgIcon(
                svg = svgResource(MR.assets.icons.plus),
                modifier = Modifier
                    .size(MaterialTheme.dimens.grid_1_5),
                contentDescription = null
            )
        }
    }
}
