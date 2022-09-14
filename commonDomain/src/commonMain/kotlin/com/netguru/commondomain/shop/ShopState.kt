package com.netguru.commondomain.shop

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.shop.model.Product

data class ShopState(
    val products: List<Product> = emptyList(),
    val loading: Loading = Loading.Idle,
    val searchedProductName: String = ""
)
