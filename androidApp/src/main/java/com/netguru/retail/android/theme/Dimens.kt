package com.netguru.retail.android.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val default: Dp = 0.dp,
    val divider: Dp = 0.5.dp,
    val thickDivider: Dp = 1.dp,
    val border: Dp = 0.5.dp,
    val grid_0_25: Dp = 2.dp,
    val grid_0_5: Dp = 4.dp,
    val grid_0_75: Dp = 6.dp,
    val grid_1: Dp = 8.dp,
    val grid_1_5: Dp = 12.dp,
    val grid_2: Dp = 16.dp,
    val grid_2_5: Dp = 20.dp,
    val grid_3: Dp = 24.dp,
    val grid_3_5: Dp = 28.dp,
    val grid_4: Dp = 32.dp,
    val grid_4_5: Dp = 36.dp,
    val grid_5: Dp = 40.dp,
    val grid_5_5: Dp = 44.dp,
    val grid_6: Dp = 48.dp,
    val recently_viewed_column_imagesize: Dp = 59.dp,
    val recommended_row_height: Dp = 330.dp,
    val recommended_row_element_width: Dp = 280.dp,
    val icon_size: Dp = 24.dp
)

val LocalDimens = staticCompositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current
