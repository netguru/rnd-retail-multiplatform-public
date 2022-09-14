package com.netguru.retail.android.productDetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.product.model.ProductInfo
import com.netguru.retail.android.productDetails.components.*
import com.netguru.retail.android.shared.LoadingAwareView
import com.netguru.retail.android.theme.backgroundVariant
import com.netguru.retail.android.theme.dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductDetailsScreen(viewModel: ProductDetailsViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    BackHandler(true) {
        viewModel.handleBack()
    }

    ProductDetailsLayout(
        onBackClick = viewModel::handleBack,
        bottomSheetState = sheetState,
        bottomSheetContent = {
            CustomizationSheet(
                customizationInfo = viewModel.customizationInfo,
                onCollapseClick = { scope.launch { sheetState.hide() } },
                onCustomize = viewModel::handleCustomize,
                onReset = viewModel::handleReset
            )
        }
    ) {
        DetailsColumn(
            scrollableContent = {
                ProductGallery(
                    gallery = viewModel.gallery,
                    modifier = Modifier
                        .height(320.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                ProductDetails(
                    productInfo = viewModel.productInfo,
                    selectedFabricColor = Color(
                        viewModel.customizationInfo.selectedFabric.colorCode
                    ),
                    onArViewClick = viewModel::handleArViewClick,
                    onCustomizeClick = { scope.launch { sheetState.show() } },
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.dimens.grid_3,
                            end = MaterialTheme.dimens.grid_3,
                            top = MaterialTheme.dimens.grid_2
                        )
                )
                UserContentRow(
                    images = viewModel.userContentImages,
                    onSeeAllClicked = viewModel::handleSeeAllClick,
                    onImageClicked = { viewModel.handleCommunityImageClick(it) }
                )
            },
            bottomFixedContent = {
                CartAndCountButtonsRow(
                    shoppingCartInfo = viewModel.shoppingCartInfo,
                    onItemCountDecremented = viewModel::handleMinusClick,
                    onItemCountIncremented = viewModel::handlePlusClick,
                    incrementEnabled = viewModel.shoppingCartInfo.canIncrement,
                    decrementEnabled = viewModel.shoppingCartInfo.canDecrement,
                    onAddToCartClicked = viewModel::handleAddToCartClick,
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.dimens.grid_3,
                            vertical = MaterialTheme.dimens.grid_2
                        )
                )
            },
            isLoading = viewModel.loading is Loading.InProgress
        )
    }
}

@Composable
private fun DetailsColumn(
    modifier: Modifier = Modifier,
    scrollableContent: @Composable ColumnScope.() -> Unit,
    bottomFixedContent: @Composable ColumnScope.() -> Unit,
    isLoading: Boolean = true
) {
    val scrollState = rememberScrollState()

    LoadingAwareView(isLoading = isLoading) {
        Column(
            modifier.background(MaterialTheme.colors.backgroundVariant)
        ) {
            Column(
                Modifier
                    .verticalScroll(
                        scrollState
                    )
                    .fillMaxSize()
                    .weight(1f)
            ) {
                scrollableContent()
            }
            bottomFixedContent()
        }
    }
}

@Composable
private fun ProductDetails(
    productInfo: ProductInfo,
    selectedFabricColor: Color,
    onArViewClick: () -> Unit,
    onCustomizeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TitleReviewAndArRow(
            productInfo = productInfo,
            onArViewClick = onArViewClick
        )
        DescriptionAndCustomizationColumn(
            productDescription = productInfo.description,
            selectedFabricColor = selectedFabricColor,
            onCustomizeClick = onCustomizeClick,
            modifier = Modifier.padding(bottom = MaterialTheme.dimens.grid_3)
        )
    }
}
