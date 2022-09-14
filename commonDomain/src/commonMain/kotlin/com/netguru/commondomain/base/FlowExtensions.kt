package com.netguru.commondomain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

suspend fun <T> Flow<T>.asQuery(collector: (T) -> Unit): Unit =
    this
        .flowOn(Dispatchers.Main)
        .distinctUntilChanged()
        .collect { collector(it) }
