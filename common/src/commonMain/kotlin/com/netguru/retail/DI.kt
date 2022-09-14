package com.netguru.retail

import org.koin.dsl.module

val navigationModule = module {
    single { RouterProvider() }

    factory { get<RouterProvider>().mainRouter }
}
