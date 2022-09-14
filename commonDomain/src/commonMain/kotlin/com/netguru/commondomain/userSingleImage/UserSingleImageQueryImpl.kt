package com.netguru.commondomain.userSingleImage

import com.netguru.commondomain.base.Store
import com.netguru.commondomain.base.asQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class UserSingleImageQueryImpl(
    private val userContentStore: Store<UserSingleImageState>,
    private val scope: CoroutineScope
) : UserSingleImageQuery, CoroutineScope by scope {

    override fun collectImageTitle(collector: (String) -> Unit): Job = launch {
        userContentStore
            .stream
            .map { state -> state.productName }
            .asQuery(collector)
    }

    override fun collectImageUrl(collector: (String) -> Unit): Job = launch {
        userContentStore
            .stream
            .map { state -> state.imageUrl }
            .asQuery(collector)
    }
}
