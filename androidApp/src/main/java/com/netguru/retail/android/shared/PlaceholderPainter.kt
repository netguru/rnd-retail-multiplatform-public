package com.netguru.retail.android.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import com.netguru.commonResources.MR

@Composable
fun placeholderPainter(): Painter? {
    if (LocalInspectionMode.current) {
        return painterResource(MR.images.armchair_placeholder.drawableResId)
    }
    return null
}
