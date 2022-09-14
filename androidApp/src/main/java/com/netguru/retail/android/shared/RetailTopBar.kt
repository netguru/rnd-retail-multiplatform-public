package com.netguru.retail.android.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.commonResources.MR
import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RetailTopBar(
    onClickNav: () -> Unit,
    modifier: Modifier = Modifier,
    navIcon: AssetResource = MR.assets.icons.back,
    actionIcon: AssetResource = MR.assets.icons.outlined_shoppingbag_alt
) {
    TopBarRow(modifier = modifier) {
        RetailIconButton(
            onClick = onClickNav,
            icon = svgResource(navIcon),
            contentDescription = stringResource(MR.strings.desc_menu)
        )
        RetailIconButton(
            onClick = {},
            icon = svgResource(actionIcon),
            contentDescription = stringResource(MR.strings.desc_notifications)
        )
    }
}
