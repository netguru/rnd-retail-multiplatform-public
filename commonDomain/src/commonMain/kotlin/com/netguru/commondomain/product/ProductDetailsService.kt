package com.netguru.commondomain.product

import com.netguru.commondomain.product.model.CustomizationRequest
import kotlinx.coroutines.Job

interface ProductDetailsService {
    fun loadData(productId: String): Job
    fun goBack(): Job
    fun showInAr(): Job
    fun decreaseSelectedAmount(): Job
    fun increaseSelectedAmount(): Job
    fun selectConfiguration(request: CustomizationRequest): Job
    fun resetConfiguration(): Job
    fun openUserContent(): Job
    fun showFullscreenImage(imageUrl: String): Job
}
