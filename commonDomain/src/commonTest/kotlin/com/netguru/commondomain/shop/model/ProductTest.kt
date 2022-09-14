package com.netguru.commondomain.shop.model

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProductTest {

    private val product = Product(name = "Lorem ipsum")

    @Test
    fun `GIVEN product WHEN search name is part of product name THEN is matching`() {
        val searchedName = "lorem"

        assertTrue(product.isMatching(searchedName))
    }

    @Test
    fun `GIVEN product WHEN search name is not part of product name THEN is not matching`() {
        val searchedName = "dot"

        assertFalse(product.isMatching(searchedName))
    }

    @Test
    fun `GIVEN product with one highlight THEN is highlighted with only one highlight type`() {
        val product = Product(highlights = setOf(Highlight.NEW))

        assertTrue(product.isHighlighted(Highlight.NEW))
        assertFalse(product.isHighlighted(Highlight.RECOMMENDED))
    }

    @Test
    fun `GIVEN product with multiple highlights THEN is highlighted with these multiple types`() {
        val product = Product(highlights = setOf(Highlight.NEW, Highlight.RECOMMENDED))

        assertTrue(product.isHighlighted(Highlight.NEW))
        assertTrue(product.isHighlighted(Highlight.RECOMMENDED))
    }
}
