package com.netguru.commondomain.userContent

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.base.asQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class UserContentQueryImpl(
    private val userContentStore: Store<UserContentState>,
    private val scope: CoroutineScope
) : UserContentQuery, CoroutineScope by scope {

    override fun collectUserContentImages(collector: (List<String>) -> Unit) = launch {
        userContentStore
            .stream
            .map { state ->
                state.images
                    .map { userContentImage -> userContentImage.imageUrl }
            }
            .asQuery(collector)
    }

    override fun collectUserContentTitle(collector: (String) -> Unit) = launch {
        userContentStore
            .stream
            .map { state -> state.title }
            .asQuery(collector)
    }

    override fun collectLoading(collector: (Loading) -> Unit) = launch {
        userContentStore
            .stream
            .map { state -> state.loading }
            .asQuery(collector)
    }
}
