package com.netguru.retail.android.productDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.netguru.commonResources.MR
import com.netguru.retail.android.theme.dimens
import com.netguru.retail.android.ui.shared.ClickableText
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DescriptionAndCustomizationColumn(
    productDescription: String,
    selectedFabricColor: Color,
    modifier: Modifier = Modifier,
    onCustomizeClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(MR.strings.details_description),
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(bottom = MaterialTheme.dimens.grid_1)
        )
        SelectionContainer {
            Text(
                text = productDescription,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
        }
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(top = MaterialTheme.dimens.grid_4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ColorCircle(
                isSelected = true,
                onCircleSelected = onCustomizeClick,
                style = CircleStyle.colored(selectedFabricColor)
            )
            Spacer(Modifier.width(MaterialTheme.dimens.grid_1_5))
            ClickableText(
                text = stringResource(MR.strings.details_customize),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = MaterialTheme.dimens.grid_1_5),
                onTextClicked = onCustomizeClick
            )
        }
    }
}
