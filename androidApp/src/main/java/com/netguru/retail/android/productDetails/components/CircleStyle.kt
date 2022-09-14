package com.netguru.retail.android.productDetails.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.netguru.retail.android.theme.dimens

@Immutable
data class CircleStyle(
    val innerRadius: Dp,
    val borderRadius: Dp,
    val innerColor: Color,
    val borderColor: Color
) {
    companion object {
        @Composable
        fun labeled() = CircleStyle(
            innerRadius = MaterialTheme.dimens.grid_1,
            borderRadius = MaterialTheme.dimens.grid_1_5,
            innerColor = MaterialTheme.colors.secondary,
            borderColor = MaterialTheme.colors.onSecondary
        )

        @Composable
        fun colored(color: Color) = labeled().copy(
            innerColor = color
        )
    }
}
