package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.FadingAsyncImage
import com.netguru.retail.android.theme.dimens
import com.netguru.retail.android.ui.shared.ClickableText
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun UserContentRow(
    images: List<String>,
    onSeeAllClicked: () -> Unit,
    onImageClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = MaterialTheme.dimens.grid_1_5)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.dimens.grid_3,
                    end = MaterialTheme.dimens.grid_3,
                    bottom = MaterialTheme.dimens.grid_2
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(MR.strings.details_posts),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.alignByBaseline()
            )
            ClickableText(
                text = stringResource(MR.strings.details_see_all),
                modifier = Modifier.alignByBaseline(),
                onTextClicked = onSeeAllClicked
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.grid_2)
        ) {
            itemsIndexed(
                items = images,
                key = { _, image -> image }
            ) { index, image ->
                FadingAsyncImage(
                    imageUrl = image,
                    modifier = Modifier
                        .offset(x = MaterialTheme.dimens.grid_3)
                        .size(
                            width = 160.dp,
                            height = 120.dp
                        )
                        .clip(MaterialTheme.shapes.small)
                        .clickable { onImageClicked(image) },
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
                if (index == images.lastIndex) {
                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.grid_5))
                }
            }
        }
    }
}
