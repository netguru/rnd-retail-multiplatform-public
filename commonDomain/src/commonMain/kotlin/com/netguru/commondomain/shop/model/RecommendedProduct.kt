package com.netguru.commondomain.shop.model

data class RecommendedProduct(
    val id: String = "",
    val name: String = "",
    val price: Price = Price(),
    val thumbnailUrl: String = "",
    val isNew: Boolean = false
) {

    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        price = product.price,
        thumbnailUrl = product.thumbnailUrl,
        isNew = product.isHighlighted(Highlight.NEW)
    )
}
