package com.netguru.commondomain.ar

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.base.asQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class ArQueryImpl(
    private val arStore: Store<ArState>,
    private val scope: CoroutineScope
) : ArQuery, CoroutineScope by scope {

    override fun collectArModel(collector: (String) -> Unit): Job = launch {
        arStore
            .stream
            .map { it.model.getPlatformArModel() }
            .asQuery(collector)
    }

    override fun collectArLoadingState(collector: (Loading) -> Unit) = launch {
        arStore
            .stream
            .map { it.loading }
            .asQuery(collector)
    }

    override fun collectShowArOnboarding(collector: (Boolean) -> Unit) = launch {
        arStore
            .stream
            .map { it.shouldShowOnboarding }
            .asQuery(collector)
    }
}
