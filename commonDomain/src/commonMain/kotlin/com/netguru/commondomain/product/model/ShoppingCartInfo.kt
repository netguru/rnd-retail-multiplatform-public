package com.netguru.commondomain.product.model

import com.netguru.commondomain.shop.model.Price

data class ShoppingCartInfo(
    val numOfItemsInCart: Int = 1,
    val totalPrice: Price = Price(),
    val canDecrement: Boolean = true,
    val canIncrement: Boolean = true
) {
    companion object {
        val empty: ShoppingCartInfo
            get() = ShoppingCartInfo()
    }
}
