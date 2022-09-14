package com.netguru.commondomain.userContent

import com.netguru.commondomain.userContent.model.UserContentRequest
import kotlinx.coroutines.Job

interface UserContentService {

    fun loadUserContent(userContentRequest: UserContentRequest): Job

    fun showImageInFullscreen(imageUrl: String): Job

    fun goBack(): Job
}
