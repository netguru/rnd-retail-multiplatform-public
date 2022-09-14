package com.netguru.retail.android.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.netguru.commonResources.MR
import com.netguru.commondomain.shop.model.Price
import com.netguru.commondomain.shop.model.RecommendedProduct
import com.netguru.retail.android.mocks.ShopQueryMock
import com.netguru.retail.android.mocks.ShopServiceMock
import com.netguru.retail.android.shared.RetailHorizontalDivider
import com.netguru.retail.android.shared.RetailIconButton
import com.netguru.retail.android.shared.SvgIcon
import com.netguru.retail.android.shared.svgResource
import com.netguru.retail.android.theme.RetailTheme
import com.netguru.retail.android.theme.dimens
import com.netguru.retail.android.theme.setSystemBarColor
import dev.icerock.moko.resources.compose.stringResource

private const val NO_MATCHING_PRODUCTS_ALPHA = 0.5f

@Composable
fun ShopScreen(viewModel: ShopViewModel = viewModel()) {
    setSystemBarColor(
        Color.White,
        Color.White
    )

    Column(
        Modifier
            .padding(top = MaterialTheme.dimens.grid_1)
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        SearchBar(
            searchText = viewModel.searchText,
            onSearchTextChanged = viewModel::handleSearchProduct
        )
        val recommendedProducts = viewModel.recommendedProducts
        val recentlyViewedProducts = viewModel.recentlyViewedProducts

        LazyColumn {
            if (recommendedProducts.isNotEmpty()) {
                item { SectionTitle(stringResource(MR.strings.recommended)) }
                item {
                    RecommendedRow(
                        recommendedProducts = recommendedProducts,
                        onProductClicked = viewModel::handleProductClick
                    )
                }
            }
            if (recentlyViewedProducts.isNotEmpty()) {
                item { SectionTitle(stringResource(MR.strings.recently_viewed)) }
                items(
                    items = recentlyViewedProducts,
                    key = { it.id }
                ) {
                    RecentlyViewedProductView(
                        state = it,
                        onItemClicked = { viewModel.handleProductClick(it) }
                    )
                }
                item { RetailHorizontalDivider() }
            }
        }
        if (recommendedProducts.isEmpty() && recentlyViewedProducts.isEmpty()) {
            NoMatchingProductsView()
        }
    }
}

@Composable
private fun ColumnScope.NoMatchingProductsView() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .align(Alignment.CenterHorizontally),
        contentAlignment = Alignment.Center
    ) {
        Column {
            SvgIcon(
                modifier = Modifier
                    .size(128.dp)
                    .alpha(NO_MATCHING_PRODUCTS_ALPHA)
                    .align(Alignment.CenterHorizontally),
                svg = svgResource(MR.assets.icons.search),
                contentDescription = null
            )
            Text(
                text = stringResource(MR.strings.no_matching_products),
                style = MaterialTheme.typography.body1.copy(fontSize = 22.sp),
                color = MaterialTheme.colors.secondary.copy(alpha = NO_MATCHING_PRODUCTS_ALPHA)
            )
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(
            top = MaterialTheme.dimens.grid_2,
            bottom = MaterialTheme.dimens.grid_2,
            start = MaterialTheme.dimens.grid_3
        ),
        color = MaterialTheme.colors.secondary
    )
}

@Composable
private fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimens.grid_3,
                vertical = MaterialTheme.dimens.grid_1
            ),
        value = searchText,
        onValueChange = onSearchTextChanged,
        placeholder = {
            Text(
                text = stringResource(MR.strings.desc_search),
                style = MaterialTheme.typography.body1
            )
        },
        trailingIcon = {
            if (searchText.isEmpty()) {
                SvgIcon(
                    modifier = Modifier.size(MaterialTheme.dimens.icon_size),
                    svg = svgResource(MR.assets.icons.search),
                    contentDescription = stringResource(MR.strings.desc_search)
                )
            } else {
                RetailIconButton(
                    onClick = { onSearchTextChanged("") },
                    icon = svgResource(MR.assets.icons.close),
                    contentDescription = stringResource(MR.strings.desc_search)
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onPrimary,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = MaterialTheme.shapes.small,
        singleLine = true
    )
}

@Preview
@Composable
private fun Preview() {
    val viewModel = ShopViewModel(
        shopQuery = ShopQueryMock(),
        shopService = ShopServiceMock()
    )
    viewModel.recommendedProducts = listOf(
        RecommendedProduct(
            id = "1",
            name = "Whinston",
            price = Price(amount = 3245.8f)
        )
    )
    RetailTheme {
        ShopScreen(viewModel = viewModel)
    }
}
