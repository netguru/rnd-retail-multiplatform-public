package com.netguru.retail

import kotlinx.coroutines.Job

object MockProvider {

    val job: Job
        get() = Job()
}
