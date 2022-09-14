package com.netguru.retail.android.shop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.netguru.commondomain.shop.ShopQuery
import com.netguru.commondomain.shop.ShopService
import com.netguru.commondomain.shop.model.RecentlyViewedProduct
import com.netguru.commondomain.shop.model.RecommendedProduct
import com.netguru.retail.Provider
import com.netguru.retail.android.viewModel.query

class ShopViewModel(
    private val shopQuery: ShopQuery = Provider.shopQuery,
    private val shopService: ShopService = Provider.shopService
) : ViewModel() {

    var recommendedProducts by query(shopQuery::collectRecommendedProducts, emptyList())
    var recentlyViewedProducts by query(shopQuery::collectRecentlyViewedProducts, emptyList())
    var searchText by query(shopQuery::collectSearchedProductName, "")

    init {
        shopService.loadData()
    }

    fun handleSearchProduct(productName: String) {
        shopService.searchProduct(productName)
    }

    fun handleProductClick(product: RecommendedProduct) {
        shopService.openProductDetails(product.id)
    }

    fun handleProductClick(product: RecentlyViewedProduct) {
        shopService.openProductDetails(product.id)
    }
}
