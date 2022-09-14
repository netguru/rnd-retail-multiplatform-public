package com.netguru.retail.android.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.netguru.commonResources.MR

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(
        Font(MR.fonts.GoogleSans.regular.fontResourceId),
        Font(MR.fonts.GoogleSans.medium.fontResourceId, FontWeight.Medium),
        Font(MR.fonts.GoogleSans.bold.fontResourceId, FontWeight.Bold)
    ),
    body1 = TextStyle(
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.005).sp
    ),
    h2 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
)
