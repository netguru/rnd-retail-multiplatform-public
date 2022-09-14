package com.netguru.retail.android.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.netguru.commonResources.MR
import com.netguru.commondomain.shop.model.Price
import com.netguru.commondomain.shop.model.RecentlyViewedProduct
import com.netguru.retail.android.shared.FadingAsyncImage
import com.netguru.retail.android.shared.RetailHorizontalDivider
import com.netguru.retail.android.shared.format
import com.netguru.retail.android.shared.placeholderPainter
import com.netguru.retail.android.theme.RetailTheme
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RecentlyViewedProductView(
    state: RecentlyViewedProduct,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        RetailHorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked() }
                .padding(
                    vertical = MaterialTheme.dimens.grid_1_5,
                    horizontal = MaterialTheme.dimens.grid_4
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FadingAsyncImage(
                imageUrl = state.thumbnailUrl,
                contentDescription = stringResource(MR.strings.desc_product_preview),
                modifier = Modifier
                    .size(MaterialTheme.dimens.recently_viewed_column_imagesize)
                    .padding(end = MaterialTheme.dimens.grid_3_5)
                    .weight(0.26f),
                contentScale = ContentScale.Fit,
                placeholder = placeholderPainter()
            )
            Column(
                modifier = Modifier.weight(0.52f)
            ) {
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = MaterialTheme.dimens.grid_0_5)
                )
                Text(
                    text = state.category,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
                )
            }
            Text(
                text = state.price.format(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(0.22f),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallProductColumnPreview() {
    RetailTheme {
        RecentlyViewedProductView(
            state = RecentlyViewedProduct(
                id = "1",
                thumbnailUrl = "",
                name = "Mid Century Modern Armchair",
                category = "Sofas",
                price = Price(amount = 264.00f)
            ),
            onItemClicked = {}
        )
    }
}
