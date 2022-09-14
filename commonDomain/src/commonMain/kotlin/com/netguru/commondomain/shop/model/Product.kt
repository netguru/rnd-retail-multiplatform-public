package com.netguru.commondomain.shop.model

import com.soywiz.klock.DateTime

data class Product(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val thumbnailUrl: String = "",
    val price: Price = Price(),
    val highlights: Set<Highlight> = emptySet(),
    val lastViewed: DateTime? = null
) {

    fun isMatching(searchedName: String): Boolean =
        name.lowercase().contains(searchedName.lowercase())

    fun isHighlighted(highlight: Highlight): Boolean =
        highlights.contains(highlight)
}
