package com.netguru.retail.android.productDetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import com.netguru.retail.android.theme.dimens

@Composable
fun LabeledVariantCircles(
    selectedCircleLabel: String,
    circles: List<LabeledCircleItem>,
    modifier: Modifier = Modifier,
    onVariantSelected: (label: String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        circles.forEach { circle ->
            AnimatedVisibility(visible = circle.isDisplayed) {
                LabeledCircle(
                    label = circle.label,
                    isSelected = selectedCircleLabel == circle.label,
                    onCircleSelected = { onVariantSelected(circle.label) }
                )
            }
        }
    }
}

@Immutable
data class LabeledCircleItem(
    val label: String,
    val isDisplayed: Boolean
)

private const val BORDER_STROKE = 3f

@Composable
fun LabeledCircle(
    label: String,
    isSelected: Boolean,
    onCircleSelected: () -> Unit,
    style: CircleStyle = CircleStyle.labeled()
) {
    Row(
        Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onCircleSelected() }
            .padding(vertical = MaterialTheme.dimens.grid_1)
    ) {
        CircleCanvas(
            isSelected = isSelected,
            style = style
        )
        Spacer(Modifier.width(MaterialTheme.dimens.grid_2))
        Text(
            text = label,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
private fun CircleCanvas(
    isSelected: Boolean,
    style: CircleStyle
) {
    val size = style.borderRadius * 2
    Canvas(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
    ) {
        drawCircle(
            color = style.borderColor,
            radius = style.borderRadius.toPx(),
            style = Stroke(BORDER_STROKE)
        )
        if (isSelected) {
            drawCircle(
                color = style.innerColor,
                radius = style.innerRadius.toPx()
            )
        }
    }
}
