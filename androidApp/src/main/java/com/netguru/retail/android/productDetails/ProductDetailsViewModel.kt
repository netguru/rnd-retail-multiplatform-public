package com.netguru.retail.android.productDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.product.ProductDetailsQuery
import com.netguru.commondomain.product.ProductDetailsService
import com.netguru.commondomain.product.model.CustomizationInfo
import com.netguru.commondomain.product.model.CustomizationRequest
import com.netguru.commondomain.product.model.ProductInfo
import com.netguru.commondomain.product.model.ShoppingCartInfo
import com.netguru.retail.Provider
import com.netguru.retail.android.viewModel.query

class ProductDetailsViewModel(
    private val productDetailsService: ProductDetailsService = Provider.productDetailsService,
    private val productQuery: ProductDetailsQuery = Provider.productDetailsQuery
) : ViewModel() {

    var productInfo by query(productQuery::collectProductInfo, ProductInfo())
    var gallery by query(productQuery::collectGallery, emptyList())
    var customizationInfo by query(productQuery::collectCustomizationInfo, CustomizationInfo())
    var shoppingCartInfo by query(productQuery::collectShoppingCartInfo, ShoppingCartInfo())
    var userContentImages by query(productQuery::collectUserContentImages, emptyList())
    var loading by query(productQuery::collectLoading, Loading.Idle)

    fun handleBack() {
        productDetailsService.goBack()
    }

    fun handleMinusClick() {
        productDetailsService.decreaseSelectedAmount()
    }

    fun handlePlusClick() {
        productDetailsService.increaseSelectedAmount()
    }

    fun handleAddToCartClick() = Unit

    fun handleArViewClick() {
        productDetailsService.showInAr()
    }

    fun handleCustomize(selection: CustomizationRequest) {
        productDetailsService.selectConfiguration(selection)
    }

    fun handleReset() {
        productDetailsService.resetConfiguration()
    }

    fun handleCommunityImageClick(imageUrl: String) {
        productDetailsService.showFullscreenImage(imageUrl)
    }

    fun handleSeeAllClick() {
        productDetailsService.openUserContent()
    }
}
