package com.netguru.commondomain.userContent

import com.netguru.commondomain.base.Loading

data class UserContentState(
    val title: String = "",
    val images: List<UserContentImage> = emptyList(),
    val loading: Loading = Loading.Idle
)
