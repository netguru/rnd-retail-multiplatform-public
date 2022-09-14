package com.netguru.retail.android.productDetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.netguru.retail.android.theme.dimens

@Composable
fun ColorVariantCircles(
    selectedCircleName: String,
    circleItems: List<ColorCircleItem>,
    modifier: Modifier = Modifier,
    onColorVariantSelected: (variantName: String) -> Unit
) {
    Row(modifier) {
        circleItems.forEach { circle ->
            AnimatedVisibility(circle.isDisplayed) {
                Row {
                    ColorCircle(
                        isSelected = selectedCircleName == circle.name,
                        onCircleSelected = { onColorVariantSelected(circle.name) },
                        style = CircleStyle.colored(circle.color)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.grid_1_5))
                }
            }
        }
    }
}

@Immutable
data class ColorCircleItem(
    val name: String,
    val color: Color,
    val isDisplayed: Boolean = true
)

private const val BORDER_STROKE = 3f

@Composable
fun ColorCircle(
    isSelected: Boolean,
    onCircleSelected: () -> Unit,
    style: CircleStyle
) {
    val size = style.borderRadius * 2
    Canvas(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .clickable { onCircleSelected() }
    ) {
        if (isSelected) {
            drawCircle(
                color = style.borderColor,
                radius = style.borderRadius.toPx(),
                style = Stroke(BORDER_STROKE)
            )
            drawCircle(
                color = style.innerColor,
                radius = style.innerRadius.toPx()
            )
        } else {
            drawCircle(
                color = style.innerColor,
                radius = style.borderRadius.toPx()
            )
        }
    }
}
