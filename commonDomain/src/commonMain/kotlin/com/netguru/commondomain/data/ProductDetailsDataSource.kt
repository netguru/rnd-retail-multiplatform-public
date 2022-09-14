package com.netguru.commondomain.data

import com.netguru.commondomain.product.model.ProductDetails

interface ProductDetailsDataSource {
    suspend fun getForProduct(productId: String): ProductDetails
}
