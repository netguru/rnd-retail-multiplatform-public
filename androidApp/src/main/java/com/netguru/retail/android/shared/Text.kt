package com.netguru.retail.android.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun ClickableText(
    text: String,
    onTextClicked: () -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.body2.copy(
        color = MaterialTheme.colors.secondary,
        fontWeight = FontWeight.Bold
    ),
    textDecoration: TextDecoration? = TextDecoration.Underline
) {
    Text(
        text = text,
        style = style,
        textDecoration = textDecoration,
        modifier = modifier.clickable { onTextClicked() }
    )
}
