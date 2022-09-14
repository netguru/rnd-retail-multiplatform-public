package com.netguru.retail

import com.netguru.commondata.dataModule
import com.netguru.commondomain.ar.ArQuery
import com.netguru.commondomain.ar.ArService
import com.netguru.commondomain.domainModule
import com.netguru.commondomain.main.MainQuery
import com.netguru.commondomain.main.MainService
import com.netguru.commondomain.onboarding.OnboardingService
import com.netguru.commondomain.product.ProductDetailsQuery
import com.netguru.commondomain.product.ProductDetailsService
import com.netguru.commondomain.router.MainRouter
import com.netguru.commondomain.shop.ShopQuery
import com.netguru.commondomain.shop.ShopService
import com.netguru.commondomain.userContent.UserContentQuery
import com.netguru.commondomain.userContent.UserContentService
import com.netguru.commondomain.userSingleImage.UserSingleImageQuery
import com.netguru.commondomain.userSingleImage.UserSingleImageService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

object Provider {
    private val koin = object : KoinComponent {}

    val shopQuery: ShopQuery
        get() = koin.get()

    val productDetailsQuery: ProductDetailsQuery
        get() = koin.get()

    val shopService: ShopService
        get() = koin.get()

    val onboardingService: OnboardingService
        get() = koin.get()

    val productDetailsService: ProductDetailsService
        get() = koin.get()

    val userContentQuery: UserContentQuery
        get() = koin.get()

    val userContentService: UserContentService
        get() = koin.get()

    val arService: ArService
        get() = koin.get()

    val arQuery: ArQuery
        get() = koin.get()

    val userSingleImageQuery: UserSingleImageQuery
        get() = koin.get()

    val userSingleImageService: UserSingleImageService
        get() = koin.get()

    val mainQuery: MainQuery
        get() = koin.get()

    val mainService: MainService
        get() = koin.get()

    private val routerProvider: RouterProvider
        get() = koin.get()

    fun init() {
        startKoin {
            modules(domainModule, dataModule, navigationModule)
        }
    }

    fun registerMainRouter(router: MainRouter) {
        routerProvider.mainRouter = router
    }
}
