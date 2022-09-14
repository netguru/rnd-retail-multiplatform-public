package com.netguru.retail.android.shared

import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.caverock.androidsvg.SVG
import com.netguru.retail.android.theme.dimens

@Composable
fun RetailIconButton(
    onClick: () -> Unit,
    icon: SVG,
    contentDescription: String,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier.size(MaterialTheme.dimens.icon_size),
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        SvgIcon(
            modifier = iconModifier,
            svg = icon,
            contentDescription = contentDescription
        )
    }
}
