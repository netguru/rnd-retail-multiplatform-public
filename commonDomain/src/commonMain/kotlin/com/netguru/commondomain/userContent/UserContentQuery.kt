package com.netguru.commondomain.userContent

import com.netguru.commondomain.base.Loading
import kotlinx.coroutines.Job

interface UserContentQuery {
    fun collectUserContentTitle(collector: (String) -> Unit): Job
    fun collectLoading(collector: (Loading) -> Unit): Job
    fun collectUserContentImages(collector: (List<String>) -> Unit): Job
}
