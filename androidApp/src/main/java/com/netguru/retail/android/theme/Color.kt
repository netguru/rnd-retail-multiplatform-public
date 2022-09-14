package com.netguru.retail.android.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.netguru.commonResources.MR
import dev.icerock.moko.resources.ColorResource

internal val DarkColorPalette = darkColors(
    primary = MR.colors.primary.toComposeColor(),
    primaryVariant = MR.colors.primary_dark.toComposeColor(),
    onPrimary = MR.colors.secondary_dark.toComposeColor(),
    secondary = MR.colors.secondary.toComposeColor(),
    secondaryVariant = MR.colors.secondary_light.toComposeColor(),
    onSecondary = MR.colors.secondary_dark.toComposeColor(),
    background = MR.colors.background.toComposeColor(),
    onBackground = MR.colors.secondary_dark.toComposeColor(),
    surface = MR.colors.surface.toComposeColor(),
    onSurface = MR.colors.secondary_dark.toComposeColor(),
    error = MR.colors.alert.toComposeColor()
)

internal val LightColorPalette = lightColors(
    primary = MR.colors.primary.toComposeColor(),
    primaryVariant = MR.colors.primary_dark.toComposeColor(),
    onPrimary = MR.colors.secondary_dark.toComposeColor(),
    secondary = MR.colors.secondary.toComposeColor(),
    secondaryVariant = MR.colors.secondary_light.toComposeColor(),
    onSecondary = MR.colors.secondary_dark.toComposeColor(),
    background = MR.colors.background.toComposeColor(),
    onBackground = MR.colors.secondary_dark.toComposeColor(),
    surface = MR.colors.surface.toComposeColor(),
    onSurface = MR.colors.secondary_dark.toComposeColor(),
    error = MR.colors.alert.toComposeColor()
)

val Colors.secondaryMedium: Color
    get() = MR.colors.secondary_medium.toComposeColor()

val Colors.backgroundVariant: Color
    get() = MR.colors.background_dark.toComposeColor()

val Colors.backgroundTransparent: Color
    get() = MR.colors.background_transparent.toComposeColor()

fun ColorResource.Single.toComposeColor() = Color(color.argb)
