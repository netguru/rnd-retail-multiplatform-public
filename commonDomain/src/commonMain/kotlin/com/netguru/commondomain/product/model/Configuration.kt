package com.netguru.commondomain.product.model

data class Configuration(
    val id: String = "",
    val fabricVariant: FabricVariant = FabricVariant(),
    val woodVariant: WoodVariant = WoodVariant(),
    val sizeVariant: SizeVariant = SizeVariant()
)
