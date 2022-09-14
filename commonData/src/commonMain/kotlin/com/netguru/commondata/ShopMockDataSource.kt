package com.netguru.commondata

import com.netguru.commondomain.data.ShopDataSource
import com.netguru.commondomain.shop.model.Highlight
import com.netguru.commondomain.shop.model.Price
import com.netguru.commondomain.shop.model.Product
import com.soywiz.klock.DateTime
import com.soywiz.klock.hours

internal class ShopMockDataSource : ShopDataSource {

    private object Category {
        const val CHAIR = "Chair"
        const val COUCH = "Couch"
        const val ARMCHAIR = "Armchair"
        const val OFFICE_CHAIR = "Office chair"
    }

    private val bartolomeo = Product(
        id = "0",
        name = "Bartolomeo",
        category = Category.CHAIR,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/1fC9oWHMKuhhIoEXL_tnpG8cTeFpxNaAN",
        Price(amount = 2245.20f),
        highlights = setOf(Highlight.RECOMMENDED, Highlight.NEW)
    )

    private val guardian = Product(
        id = "1",
        name = "Guardian",
        category = Category.CHAIR,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/1ItuqTikQk9uijOsIMl_SIomOM-xtLpF2",
        price = Price(amount = 45.0f),
        highlights = setOf(Highlight.RECOMMENDED, Highlight.NEW)
    )

    private val turley = Product(
        id = "2",
        name = "Turley",
        category = Category.COUCH,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/1-JnsyB0kHpzduEJw2UBXoTDkm4c1c7D3",
        price = Price(amount = 1550.0f),
        highlights = setOf(Highlight.RECOMMENDED)
    )
    private val qwazar = Product(
        id = "3",
        name = "Qwazar",
        category = Category.CHAIR,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/1rRQBzCdVpQYb5a_lMxuZEPCeeAnMT48I",
        price = Price(amount = 35.50f),
        highlights = setOf(Highlight.RECOMMENDED)
    )
    private val invincible = Product(
        id = "4",
        name = "Invincible",
        category = Category.ARMCHAIR,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/1IkG8OJamUA41g85JK1TD3xHX8HmMEyQR",
        price = Price(amount = 142.45f),
        highlights = setOf(Highlight.RECOMMENDED),
        lastViewed = DateTime.now() - 5.hours
    )
    private val tulsa = Product(
        id = "5",
        name = "Tulsa",
        category = Category.CHAIR,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/1B-W_I2pnga6aUmvGrBntTDT1GECNrxH3",
        price = Price(amount = 25.0f),
        highlights = setOf(Highlight.RECOMMENDED)
    )
    private val henry = Product(
        id = "6",
        name = "Henry",
        category = Category.CHAIR,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/10dQuScL6FM70XNg1TbmVSfj14j7kk3QQ",
        price = Price(amount = 60.0f),
        highlights = setOf(Highlight.RECOMMENDED)
    )
    private val royalCouch = Product(
        id = "7",
        name = "Royal Couch",
        category = Category.COUCH,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/1W08RlSHBo5OdSlt_AbxRsPOkrpuR2bNP",
        price = Price(amount = 929.12f),
        highlights = emptySet(),
        lastViewed = DateTime.now() - 1.hours
    )
    private val sugar = Product(
        id = "8",
        name = "Sugar",
        category = Category.CHAIR,
        thumbnailUrl = "https://lh3.googleusercontent.com/d/144_8XrTiOQpvGDvb6BOLpvLUBABGVGux",
        price = Price(amount = 120.0f),
        highlights = setOf(Highlight.RECOMMENDED)
    )

    private var products = listOf(
        bartolomeo, guardian, turley, qwazar, invincible, tulsa, henry,
        royalCouch, sugar
    )
        .associateBy { it.id }
        .toMap()

    override suspend fun getProducts() = products.values.toList()

    override suspend fun getProduct(productId: String) =
        products[productId] ?: throw NoSuchElementException(PRODUCT_NOT_FOUND_MESSAGE)

    companion object {
        const val PRODUCT_NOT_FOUND_MESSAGE = "Product not found"
    }
}
