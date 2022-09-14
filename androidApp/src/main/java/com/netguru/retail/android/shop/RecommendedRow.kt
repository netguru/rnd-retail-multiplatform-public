package com.netguru.retail.android.shop

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.netguru.commondomain.shop.model.Price
import com.netguru.commondomain.shop.model.RecommendedProduct
import com.netguru.retail.android.theme.RetailTheme
import com.netguru.retail.android.theme.dimens

@Composable
fun RecommendedRow(
    recommendedProducts: List<RecommendedProduct>,
    onProductClicked: (RecommendedProduct) -> Unit
) {
    LazyRow(
        Modifier
            .height(MaterialTheme.dimens.recommended_row_height)
            .padding(bottom = MaterialTheme.dimens.grid_2),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.grid_1_5)
    ) {
        itemsIndexed(
            items = recommendedProducts,
            key = { _, item -> item.id }
        ) { index, item ->
            RecommendedProductView(
                state = item,
                onItemClicked = { onProductClicked(item) },
                modifier = Modifier
                    .width(MaterialTheme.dimens.recommended_row_element_width)
                    .offset(x = MaterialTheme.dimens.grid_3)
            )
            if (index == recommendedProducts.lastIndex) {
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.grid_4_5))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecommendedRowPreview() {
    RetailTheme {
        RecommendedRow(
            recommendedProducts = listOf(
                RecommendedProduct(
                    id = "1",
                    name = "Modern Dining Chair",
                    price = Price(amount = 40.00f),
                    thumbnailUrl = "https://taktcph.com/wp-content/uploads/2021/05/buymodulet12-takt-arc-0009s-0002-2021-02-18-takt-arc-front-angle-oak-oak-done.png",
                    isNew = true
                ),
                RecommendedProduct(
                    id = "2",
                    name = "Sofa",
                    price = Price(amount = 60.00f),
                    thumbnailUrl = "https://taktcph.com/wp-content/uploads/2021/05/buymodulet12-takt-arc-0009s-0002-2021-02-18-takt-arc-front-angle-oak-oak-done2.png",
                    isNew = false
                )
            ),
            onProductClicked = {}
        )
    }
}
