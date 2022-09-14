package com.netguru.commondomain.base

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class Store<State>(private val initialState: State) {
    private val _state = MutableStateFlow(initialState)

    val stream: Flow<State> = _state

    val state: State
        get() = _state.value

    fun update(function: (State) -> State) {
        _state.update(function)
    }

    suspend fun update(newState: State) {
        _state.emit(newState)
    }

    suspend fun reset(delayReset: Boolean = false) {
        if (delayReset) delay(timeMillis = 200)
        _state.emit(initialState)
    }
}
