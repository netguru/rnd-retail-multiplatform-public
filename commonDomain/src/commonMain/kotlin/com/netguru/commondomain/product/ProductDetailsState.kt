package com.netguru.commondomain.product

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.product.model.Configuration
import com.netguru.commondomain.product.model.ProductDetails
import com.netguru.commondomain.shop.model.Product

data class ProductDetailsState(
    val product: Product = Product(),
    val productDetails: ProductDetails = ProductDetails(),
    val selectedAmount: Int = 1,
    val selectedConfigurationIndex: Int = 0,
    val loading: Loading = Loading.Idle
) {
    val selectedConfiguration
        get() = productDetails.possibleConfigurations.getOrNull(selectedConfigurationIndex)
            ?: Configuration()
}
