package com.netguru.commondomain

import com.netguru.commondomain.ar.*
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.main.*
import com.netguru.commondomain.onboarding.OnboardingService
import com.netguru.commondomain.onboarding.OnboardingServiceImpl
import com.netguru.commondomain.product.*
import com.netguru.commondomain.shop.*
import com.netguru.commondomain.userContent.*
import com.netguru.commondomain.userSingleImage.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

const val SHOP_STORE = "shop_store"
const val PRODUCT_DETAILS_STORE = "product_details_store"
const val USER_CONTENT_STORE = "user_content_store"
const val AR_STORE = "ar_store"
const val USER_SINGLE_IMAGE_STORE = "user_single_image_store"
const val MAIN_STORE = "main_store"

val domainModule = module {

    single { Dispatchers.Default }

    single {
        CoroutineScope(Dispatchers.Default + SupervisorJob())
    }

    single(named(SHOP_STORE)) {
        Store(ShopState())
    }

    single(named(PRODUCT_DETAILS_STORE)) {
        Store(ProductDetailsState())
    }

    single(named(AR_STORE)) {
        Store(ArState())
    }

    single(named(USER_CONTENT_STORE)) {
        Store(UserContentState())
    }

    single(named(USER_SINGLE_IMAGE_STORE)) {
        Store(UserSingleImageState())
    }

    single(named(MAIN_STORE)) {
        Store(MainState())
    }

    factory {
        ArQueryImpl(get(named(AR_STORE)), get())
    } bind ArQuery::class

    factory {
        ArServiceImpl(get(named(AR_STORE)), get(), get(), get(), get())
    } bind ArService::class

    factory {
        ShopQueryImpl(get(named(SHOP_STORE)), get())
    } bind ShopQuery::class

    factory {
        ShopServiceImpl(get(named(SHOP_STORE)), get(), get(), get(), get())
    } bind ShopService::class

    factory {
        ProductDetailsQueryImpl(get(named(PRODUCT_DETAILS_STORE)), get())
    } bind ProductDetailsQuery::class

    factory {
        ProductDetailsServiceImpl(
            get(named(PRODUCT_DETAILS_STORE)),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    } bind ProductDetailsService::class

    factory {
        UserContentQueryImpl(get(named(USER_CONTENT_STORE)), get())
    } bind UserContentQuery::class

    factory {
        UserContentServiceImpl(get(named(USER_CONTENT_STORE)), get(), get(), get(), get())
    } bind UserContentService::class

    factory {
        UserSingleImageQueryImpl(get(named(USER_SINGLE_IMAGE_STORE)), get())
    } bind UserSingleImageQuery::class

    factory {
        UserSingleImageServiceImpl(get(named(USER_SINGLE_IMAGE_STORE)), get(), get())
    } bind UserSingleImageService::class

    factory {
        OnboardingServiceImpl(get(), get(), get())
    } bind OnboardingService::class

    factory {
        MainQueryImpl(get(named(MAIN_STORE)), get())
    } bind MainQuery::class

    factory {
        MainServiceImpl(get(named(MAIN_STORE)), get(), get())
    } bind MainService::class
}
