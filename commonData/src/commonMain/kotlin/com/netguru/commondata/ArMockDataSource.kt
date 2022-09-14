package com.netguru.commondata

import com.netguru.commondomain.ar.model.ArModelConfiguration
import com.netguru.commondomain.data.ArDataSource

class ArMockDataSource : ArDataSource {
    override suspend fun getArModel(arModelConfiguration: ArModelConfiguration): String {
        return "bartolomeo" + when (arModelConfiguration.configurationId) {
            "1" -> "_grey_beige"
            "2" -> "_grey_dark"
            "3" -> "_grey_orange"
            "4" -> "_black_beige"
            "5" -> "_black_dark"
            "6" -> "_black_orange"
            "7" -> "_green_beige"
            "8" -> "_green_dark"
            "9" -> "_green_orange"
            else -> "_grey_beige"
        }
    }
}
