package com.netguru.commondomain.ar

import com.netguru.commondomain.ar.model.ArModelConfiguration
import kotlinx.coroutines.Job

interface ArService {

    fun loadAr(arModelConfiguration: ArModelConfiguration): Job
    fun finishArOnboarding(): Job
    fun goBack(): Job
}
