package com.netguru.retail.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RetailTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    // Can be later edited if we decide to support different screen sizes, e.g.: tablets
    val screenDimens = Dimens()

    CompositionLocalProvider(
        LocalDimens provides screenDimens
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun setSystemBarColor(statusColor: Color, navigationColor: Color, darkIcons: Boolean = true) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(statusColor, darkIcons)
        systemUiController.setNavigationBarColor(navigationColor, darkIcons)
    }
}
