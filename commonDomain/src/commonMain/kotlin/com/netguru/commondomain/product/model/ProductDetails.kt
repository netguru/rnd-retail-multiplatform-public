package com.netguru.commondomain.product.model

import com.netguru.commondomain.userContent.UserContentImage

data class ProductDetails(
    val productId: String = "",
    val description: String = "",
    val averageStars: Float = 0f,
    val numOfReviews: Int = 0,
    val defaultConfiguration: Configuration = Configuration(),
    val possibleConfigurations: List<Configuration> = emptyList(),
    val gallery: List<GalleryImage> = emptyList(),
    val userContentImages: List<UserContentImage> = emptyList()
)
