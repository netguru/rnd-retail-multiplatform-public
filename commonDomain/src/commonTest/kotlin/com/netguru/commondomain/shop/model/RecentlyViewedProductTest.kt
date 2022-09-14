package com.netguru.commondomain.shop.model

import kotlin.test.Test
import kotlin.test.assertEquals

class RecentlyViewedProductTest {

    @Test
    fun `GIVEN product WHEN create recently viewed product THEN map data correctly`() {
        val product = Product(
            id = "1",
            name = "Product 1",
            category = "Category 1",
            price = Price(amount = 40.99f),
            thumbnailUrl = "https://test.png"
        )

        val recentlyViewedProduct = RecentlyViewedProduct(product)

        recentlyViewedProduct.run {
            assertEquals(product.id, id)
            assertEquals(product.name, name)
            assertEquals(product.category, category)
            assertEquals(product.price, price)
            assertEquals(product.thumbnailUrl, thumbnailUrl)
        }
    }
}
