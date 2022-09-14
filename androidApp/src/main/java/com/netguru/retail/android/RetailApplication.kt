package com.netguru.retail.android

import android.app.Application
import com.netguru.retail.Provider

class RetailApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Provider.init()
    }
}
