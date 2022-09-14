package com.netguru.retail.android.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.retail.android.theme.dimens

@Composable
fun TopBarRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.dimens.grid_2,
                bottom = MaterialTheme.dimens.grid_1,
                start = MaterialTheme.dimens.grid_1_5,
                end = MaterialTheme.dimens.grid_1_5
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
}
