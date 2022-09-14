package com.netguru.commondomain.userSingleImage

import kotlinx.coroutines.Job

interface UserSingleImageQuery {
    fun collectImageTitle(collector: (String) -> Unit): Job
    fun collectImageUrl(collector: (String) -> Unit): Job
}
