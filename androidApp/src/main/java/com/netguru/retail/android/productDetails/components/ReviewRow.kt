package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.SvgIcon
import com.netguru.retail.android.shared.svgResource
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.desc

@Composable
fun ReviewRow(
    averageReview: Float,
    totalReviewCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = averageReview.toString(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(end = MaterialTheme.dimens.grid_1)
        )

        for (i in 0..4) {
            val icon = remember(averageReview) {
                when {
                    averageReview - i > 0.5 -> MR.assets.icons.filled_star
                    averageReview - i in 0.2..0.5 -> MR.assets.icons.half_filled_star
                    else -> MR.assets.icons.outlined_star
                }
            }

            SvgIcon(
                svg = svgResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(MaterialTheme.dimens.grid_2_5)
                    .padding(end = MaterialTheme.dimens.grid_0_5)
            )
        }

        Text(
            text = "($totalReviewCount ${MR.strings.details_review.desc().localized()})",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = MaterialTheme.dimens.grid_0_5)
        )
    }
}
