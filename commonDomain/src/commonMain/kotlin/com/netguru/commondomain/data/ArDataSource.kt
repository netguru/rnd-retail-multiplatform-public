package com.netguru.commondomain.data

import com.netguru.commondomain.ar.model.ArModelConfiguration

interface ArDataSource {

    suspend fun getArModel(arModelConfiguration: ArModelConfiguration): String
}
