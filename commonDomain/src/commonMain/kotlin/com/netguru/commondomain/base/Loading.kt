package com.netguru.commondomain.base

sealed class Loading {

    object Idle : Loading()

    object InProgress : Loading()

    data class Failed(val error: Throwable) : Loading()
}
