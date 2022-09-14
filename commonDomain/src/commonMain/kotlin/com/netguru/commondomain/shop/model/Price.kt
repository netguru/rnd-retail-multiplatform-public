package com.netguru.commondomain.shop.model

data class Price(
    val currency: String = "$",
    val amount: Float = 0f
)
