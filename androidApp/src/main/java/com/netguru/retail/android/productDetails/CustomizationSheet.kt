package com.netguru.retail.android.productDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.netguru.commonResources.MR
import com.netguru.commondomain.product.model.CustomizationInfo
import com.netguru.commondomain.product.model.CustomizationRequest
import com.netguru.commondomain.product.model.VariantType
import com.netguru.retail.android.productDetails.components.ColorCircleItem
import com.netguru.retail.android.productDetails.components.ColorVariantCircles
import com.netguru.retail.android.productDetails.components.LabeledCircleItem
import com.netguru.retail.android.productDetails.components.LabeledVariantCircles
import com.netguru.retail.android.shared.RetailIconButton
import com.netguru.retail.android.shared.svgResource
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.format

@Composable
fun CustomizationSheet(
    customizationInfo: CustomizationInfo,
    onCollapseClick: () -> Unit,
    onCustomize: (CustomizationRequest) -> Unit,
    onReset: () -> Unit
) {
    Scaffold(
        topBar = {
            Column(
                Modifier
                    .padding(top = MaterialTheme.dimens.grid_1)
                    .padding(horizontal = MaterialTheme.dimens.grid_1)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.dimens.grid_1),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RetailIconButton(
                        onClick = onCollapseClick,
                        icon = svgResource(MR.assets.icons.chevron_arrow_down),
                        contentDescription = stringResource(MR.strings.desc_close_customization)
                    )
                    TextButton(
                        onClick = onReset,
                        contentPadding = PaddingValues(MaterialTheme.dimens.grid_2),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colors.onBackground
                        )
                    ) {
                        Text(
                            text = MR.strings.customization_reset
                                .format(customizationInfo.numOfSelectedCustomizations)
                                .toString(LocalContext.current)
                        )
                    }
                }
                Text(
                    text = stringResource(MR.strings.customization_title),
                    modifier = Modifier.padding(start = MaterialTheme.dimens.grid_2),
                    style = MaterialTheme.typography.h1
                )
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            CustomizationContents(
                customizationInfo = customizationInfo,
                onCustomize = onCustomize
            )
        }
    }
}

@Composable
fun CustomizationContents(
    customizationInfo: CustomizationInfo,
    onCustomize: (CustomizationRequest) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.dimens.grid_3)
            .padding(horizontal = MaterialTheme.dimens.grid_3)
            .verticalScroll(rememberScrollState())
    ) {
        CustomizationSection(labelText = stringResource(MR.strings.customization_fabric)) {
            ColorVariantCircles(
                selectedCircleName = customizationInfo.selectedFabric.colorName,
                circleItems = customizationInfo.availableFabrics.map {
                    ColorCircleItem(it.colorName, Color(it.colorCode), it.selectable)
                },
                onColorVariantSelected = { variantName ->
                    onCustomize(CustomizationRequest(VariantType.FABRIC, variantName))
                }
            )
        }
        CustomizationSection(labelText = stringResource(MR.strings.customization_wood)) {
            ColorVariantCircles(
                selectedCircleName = customizationInfo.selectedWoodName,
                circleItems = customizationInfo.availableWoods.map {
                    ColorCircleItem(it.colorName, Color(it.colorCode), it.selectable)
                },
                onColorVariantSelected = { variantName ->
                    onCustomize(CustomizationRequest(VariantType.WOOD, variantName))
                }
            )
        }
        CustomizationSection(labelText = stringResource(MR.strings.customization_size)) {
            LabeledVariantCircles(
                selectedCircleLabel = customizationInfo.selectedSizeName,
                circles = customizationInfo.availableSizes
                    .map { LabeledCircleItem(it.sizeName, it.selectable) },
                onVariantSelected = { variantName ->
                    onCustomize(CustomizationRequest(VariantType.SIZE, variantName))
                }
            )
        }
    }
}

@Composable
private fun CustomizationSection(
    labelText: String,
    content: @Composable () -> Unit
) {
    Text(
        text = labelText,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.secondary
    )
    Spacer(Modifier.height(MaterialTheme.dimens.grid_1_5))
    content()
    Spacer(Modifier.height(MaterialTheme.dimens.grid_4))
}
