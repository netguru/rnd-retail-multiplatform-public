package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.RetailIconButton
import com.netguru.retail.android.shared.svgResource
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ItemCountButtons(
    itemCount: Int,
    incrementEnabled: Boolean,
    decrementEnabled: Boolean,
    onItemCountDecremented: () -> Unit,
    onItemCountIncremented: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(MaterialTheme.dimens.grid_1_5)
    ) {
        RetailIconButton(
            onClick = onItemCountDecremented,
            icon = svgResource(MR.assets.icons.minus),
            contentDescription = stringResource(resource = MR.strings.details_remove_product),
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.medium
                )
                .size(MaterialTheme.dimens.icon_size)
                .align(Alignment.CenterStart),
            iconModifier = Modifier.size(
                width = MaterialTheme.dimens.grid_1_5,
                height = MaterialTheme.dimens.grid_0_5
            ),
            enabled = decrementEnabled
        )
        Text(
            text = itemCount.toString(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.align(Alignment.Center)
        )
        RetailIconButton(
            onClick = onItemCountIncremented,
            icon = svgResource(MR.assets.icons.plus),
            contentDescription = stringResource(resource = MR.strings.details_add_product),
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.onSecondary,
                    shape = MaterialTheme.shapes.medium
                )
                .size(MaterialTheme.dimens.grid_3)
                .align(Alignment.CenterEnd),
            iconModifier = Modifier.size(MaterialTheme.dimens.grid_1_5),
            enabled = incrementEnabled
        )
    }
}
