package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.SvgIcon
import com.netguru.retail.android.shared.svgResource
import com.netguru.retail.android.theme.dimens
import com.netguru.retail.android.theme.secondaryMedium
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ArViewButton(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.secondaryMedium,
                shape = MaterialTheme.shapes.small
            )
            .clickable { onButtonClicked() }
    ) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.dimens.grid_1)
                .align(Alignment.Center)
        ) {
            SvgIcon(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = MaterialTheme.dimens.grid_0_75),
                svg = svgResource(MR.assets.icons.ar_joystick),
                contentDescription = null
            )
            Text(
                text = stringResource(resource = MR.strings.details_ar_view),
                color = MaterialTheme.colors.background,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
