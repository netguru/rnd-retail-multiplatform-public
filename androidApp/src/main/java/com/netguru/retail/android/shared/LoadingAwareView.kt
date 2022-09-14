package com.netguru.retail.android.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.netguru.retail.android.theme.backgroundVariant

@Composable
fun LoadingAwareView(
    modifier: Modifier = Modifier.background(MaterialTheme.colors.backgroundVariant),
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        content()
    }
}
