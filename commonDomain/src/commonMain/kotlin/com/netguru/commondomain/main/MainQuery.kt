package com.netguru.commondomain.main

import com.netguru.commondomain.base.Loading
import kotlinx.coroutines.Job

interface MainQuery {
    fun collectShowOnboarding(collector: (Boolean) -> Unit): Job
    fun collectLoadingState(collector: (Loading) -> Unit): Job
}
