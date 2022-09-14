package com.netguru.retail.android.productAr

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.StringResource

@Immutable
data class PageArData(
    val imageAsset: AssetResource,
    val textAsset: StringResource,
    val width: Dp,
    val height: Dp
)
