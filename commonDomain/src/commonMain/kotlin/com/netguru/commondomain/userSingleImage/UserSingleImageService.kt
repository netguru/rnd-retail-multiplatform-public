package com.netguru.commondomain.userSingleImage

import com.netguru.commondomain.userSingleImage.model.SingleImageRequest
import kotlinx.coroutines.Job

interface UserSingleImageService {

    fun loadUserContent(singleImageRequest: SingleImageRequest): Job

    fun goBack(): Job
}
