package com.netguru.commondomain.main

import kotlinx.coroutines.Job

interface MainService {
    fun loadShowOnboarding(): Job
}
