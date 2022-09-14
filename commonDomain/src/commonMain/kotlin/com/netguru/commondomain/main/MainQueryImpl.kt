package com.netguru.commondomain.main

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.base.asQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class MainQueryImpl(
    private val mainStore: Store<MainState>,
    private val scope: CoroutineScope
) : MainQuery, CoroutineScope by scope {

    override fun collectShowOnboarding(collector: (Boolean) -> Unit): Job = launch {
        mainStore
            .stream
            .map { it.shouldShowOnboarding }
            .asQuery(collector)
    }

    override fun collectLoadingState(collector: (Loading) -> Unit): Job = launch {
        mainStore
            .stream
            .map { it.loading }
            .asQuery(collector)
    }
}
