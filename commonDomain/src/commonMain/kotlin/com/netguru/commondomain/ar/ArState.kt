package com.netguru.commondomain.ar

import com.netguru.commondomain.base.Loading

data class ArState(
    val model: String = "",
    val shouldShowOnboarding: Boolean = false,
    val loading: Loading = Loading.Idle
)
