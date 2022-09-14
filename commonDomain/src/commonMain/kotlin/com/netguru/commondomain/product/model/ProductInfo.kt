package com.netguru.commondomain.product.model

import com.netguru.commondomain.shop.model.Price

data class ProductInfo(
    val name: String = "",
    val price: Price = Price(),
    val description: String = "",
    val averageStars: Float = 0f,
    val numOfReviews: Int = 0
) {
    companion object {
        val empty: ProductInfo
            get() = ProductInfo()
    }
}
