package com.netguru.commondomain.shop.model

data class RecentlyViewedProduct(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val price: Price = Price(),
    val thumbnailUrl: String = ""
) {

    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        category = product.category,
        price = product.price,
        thumbnailUrl = product.thumbnailUrl
    )
}
