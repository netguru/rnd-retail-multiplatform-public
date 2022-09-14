package com.netguru.retail.android.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.retail.android.theme.dimens

@Composable
fun RetailHorizontalDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colors.secondaryVariant,
        thickness = MaterialTheme.dimens.divider
    )
}
