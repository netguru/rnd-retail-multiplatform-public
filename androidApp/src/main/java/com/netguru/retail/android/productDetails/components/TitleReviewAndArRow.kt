package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.netguru.commondomain.product.model.ProductInfo
import com.netguru.retail.android.theme.dimens

@Composable
fun TitleReviewAndArRow(
    productInfo: ProductInfo,
    onArViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            SelectionContainer {
                Text(
                    text = productInfo.name,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(bottom = MaterialTheme.dimens.grid_1)
                )
            }
            ReviewRow(
                averageReview = productInfo.averageStars,
                totalReviewCount = productInfo.numOfReviews,
                modifier = Modifier.padding(bottom = MaterialTheme.dimens.grid_2)
            )
        }
        ArViewButton(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(IntrinsicSize.Min),
            onButtonClicked = onArViewClick
        )
    }
}
