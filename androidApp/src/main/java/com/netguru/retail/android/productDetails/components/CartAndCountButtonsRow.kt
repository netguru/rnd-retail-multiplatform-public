package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.netguru.commonResources.MR
import com.netguru.commondomain.product.model.ShoppingCartInfo
import com.netguru.retail.android.shared.RetailPrimaryButton
import com.netguru.retail.android.shared.RetailVerticalDivider
import com.netguru.retail.android.shared.format
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CartAndCountButtonsRow(
    shoppingCartInfo: ShoppingCartInfo,
    incrementEnabled: Boolean,
    decrementEnabled: Boolean,
    onItemCountDecremented: () -> Unit,
    onItemCountIncremented: () -> Unit,
    onAddToCartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ItemCountButtons(
            itemCount = shoppingCartInfo.numOfItemsInCart,
            incrementEnabled = incrementEnabled,
            decrementEnabled = decrementEnabled,
            onItemCountDecremented = onItemCountDecremented,
            onItemCountIncremented = onItemCountIncremented,
            modifier = Modifier.width(128.dp)
        )
        Spacer(Modifier.width(MaterialTheme.dimens.grid_1_5))
        RetailPrimaryButton(
            onClick = onAddToCartClicked,
            modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth(),
            contentPadding = PaddingValues(vertical = MaterialTheme.dimens.grid_1_5)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(MR.strings.details_add_to_cart),
                    style = MaterialTheme.typography.button
                )
                RetailVerticalDivider(
                    modifier = Modifier.padding(horizontal = MaterialTheme.dimens.grid_1_5),
                    color = MaterialTheme.colors.onSecondary.copy(alpha = 0.35F)
                )
                Text(
                    text = shoppingCartInfo.totalPrice.format(),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}
