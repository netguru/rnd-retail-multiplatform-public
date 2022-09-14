package com.netguru.commondomain.userSingleImage

import com.netguru.commondomain.base.Store
import com.netguru.commondomain.router.MainRouter
import com.netguru.commondomain.userSingleImage.model.SingleImageRequest
import kotlinx.coroutines.*

internal class UserSingleImageServiceImpl(
    private val userSingleImageStore: Store<UserSingleImageState>,
    private val mainRouter: MainRouter,
    private val scope: CoroutineScope
) : UserSingleImageService, CoroutineScope by scope {

    override fun loadUserContent(singleImageRequest: SingleImageRequest): Job =
        launch {
            userSingleImageStore.update {
                it.copy(
                    productName = singleImageRequest.productName,
                    imageUrl = singleImageRequest.imageUrl
                )
            }
        }

    override fun goBack(): Job = launch {
        userSingleImageStore.reset()
        withContext(Dispatchers.Main) {
            mainRouter.back()
        }
    }
}
