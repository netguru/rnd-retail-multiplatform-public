package com.netguru.retail.android.shop

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.netguru.commonResources.MR
import com.netguru.commondomain.shop.model.RecommendedProduct
import com.netguru.retail.android.shared.FadingAsyncImage
import com.netguru.retail.android.shared.format
import com.netguru.retail.android.shared.placeholderPainter
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecommendedProductView(
    state: RecommendedProduct,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onItemClicked() },
        border = BorderStroke(
            width = MaterialTheme.dimens.border,
            color = MaterialTheme.colors.secondaryVariant
        ),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Column(Modifier.padding(MaterialTheme.dimens.grid_3)) {
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = state.name,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h1
                )
                if (state.isNew) {
                    Spacer(Modifier.width(MaterialTheme.dimens.grid_3))
                    NewProductMarker(Modifier.padding(top = MaterialTheme.dimens.grid_1))
                }
            }
            Spacer(Modifier.height(MaterialTheme.dimens.grid_0_5))
            Text(
                text = state.price.format(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.secondary
            )
            Spacer(Modifier.height(MaterialTheme.dimens.grid_0_5))
            FadingAsyncImage(
                imageUrl = state.thumbnailUrl,
                contentDescription = stringResource(MR.strings.desc_product_preview),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomCenter,
                placeholder = placeholderPainter()
            )
        }
    }
}

@Composable
private fun NewProductMarker(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.small,
        elevation = 0.dp
    ) {
        Text(
            text = stringResource(MR.strings.product_new).uppercase(),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.dimens.grid_0_75,
                vertical = MaterialTheme.dimens.grid_0_5
            ),
            color = MaterialTheme.colors.onPrimary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
