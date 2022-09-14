package com.netguru.retail.android.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun <T> ViewModel.query(
    collector: ((T) -> Unit) -> Job,
    initialValue: T
): MutableState<T> {
    val state = mutableStateOf(initialValue)
    viewModelScope.launch {
        collector {
            state.value = it
        }.join()
    }
    return state
}
