package com.netguru.commondomain.main

import com.netguru.commondomain.base.Loading

data class MainState(
    val shouldShowOnboarding: Boolean = false,
    val loading: Loading = Loading.InProgress
)
