package com.netguru.retail.android.shared

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.netguru.retail.android.theme.dimens

@Composable
fun RetailVerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSecondary
) {
    Divider(
        modifier = modifier
            .fillMaxHeight()
            .width(MaterialTheme.dimens.thickDivider),
        color = color
    )
}
