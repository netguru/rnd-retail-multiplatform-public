package com.netguru.commondomain.ar

import com.netguru.commondomain.base.Loading
import kotlinx.coroutines.Job

interface ArQuery {
    fun collectArModel(collector: (String) -> Unit): Job
    fun collectArLoadingState(collector: (Loading) -> Unit): Job
    fun collectShowArOnboarding(collector: (Boolean) -> Unit): Job
}
