package com.netguru.commondomain.shop.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RecommendedProductTest {

    private val product = Product(
        id = "1",
        name = "Product 1",
        category = "Category 1",
        price = Price(amount = 40.99f),
        thumbnailUrl = "http://test.pl"
    )

    @Test
    fun `GIVEN new product WHEN create recommended product THEN map data correctly`() {
        val testProduct = product.copy(
            highlights = setOf(Highlight.NEW, Highlight.RECOMMENDED)
        )

        val recommendedProduct = RecommendedProduct(testProduct)

        recommendedProduct.run {
            assertEquals(testProduct.id, id)
            assertEquals(testProduct.name, name)
            assertEquals(testProduct.price, price)
            assertEquals(testProduct.thumbnailUrl, thumbnailUrl)
            assertTrue(isNew)
        }
    }

    @Test
    fun `GIVEN not new product WHEN create recommended product THEN map data correctly`() {
        val testProduct = product.copy(
            highlights = setOf(Highlight.RECOMMENDED)
        )

        val recommendedProduct = RecommendedProduct(testProduct)

        recommendedProduct.run {
            assertEquals(testProduct.id, id)
            assertEquals(testProduct.name, name)
            assertEquals(testProduct.price, price)
            assertEquals(testProduct.thumbnailUrl, thumbnailUrl)
            assertFalse(isNew)
        }
    }
}
