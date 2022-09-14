package com.netguru.commondomain.userContent

interface UserContentDataSource {

    suspend fun getUserContentImages(productId: String): List<UserContentImage>
}
