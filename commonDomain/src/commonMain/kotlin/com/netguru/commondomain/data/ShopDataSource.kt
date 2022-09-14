package com.netguru.commondomain.data

import com.netguru.commondomain.shop.model.Product

interface ShopDataSource {

    suspend fun getProduct(productId: String): Product
    suspend fun getProducts(): List<Product>
}
