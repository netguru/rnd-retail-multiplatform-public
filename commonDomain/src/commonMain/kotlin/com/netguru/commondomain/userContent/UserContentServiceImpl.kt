package com.netguru.commondomain.userContent

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.router.MainRouter
import com.netguru.commondomain.userContent.model.UserContentRequest
import com.netguru.commondomain.userSingleImage.UserSingleImageService
import com.netguru.commondomain.userSingleImage.model.SingleImageRequest
import kotlinx.coroutines.*

internal class UserContentServiceImpl(
    private val userContentStore: Store<UserContentState>,
    private val userContentDataSource: UserContentDataSource,
    private val userSingleImageService: UserSingleImageService,
    private val mainRouter: MainRouter,
    private val scope: CoroutineScope
) : UserContentService, CoroutineScope by scope {

    override fun loadUserContent(userContentRequest: UserContentRequest): Job = launch {
        userContentStore.update {
            it.copy(
                title = userContentRequest.productName,
                loading = Loading.InProgress
            )
        }
        runCatching {
            userContentDataSource.getUserContentImages(userContentRequest.productId)
        }.onSuccess { images ->
            userContentStore.update {
                it.copy(
                    images = images,
                    loading = Loading.Idle
                )
            }
        }.onFailure { error ->
            userContentStore.update {
                it.copy(loading = Loading.Failed(error))
            }
        }
    }

    override fun showImageInFullscreen(imageUrl: String): Job = launch {
        val singleImageRequest = SingleImageRequest(
            productName = userContentStore.state.title,
            imageUrl = imageUrl
        )
        userSingleImageService.loadUserContent(singleImageRequest)
        withContext(Dispatchers.Main) {
            mainRouter.toUserSingleImage()
        }
    }

    override fun goBack(): Job = launch {
        withContext(Dispatchers.Main) {
            mainRouter.back()
        }
        userContentStore.reset(delayReset = true)
    }
}
