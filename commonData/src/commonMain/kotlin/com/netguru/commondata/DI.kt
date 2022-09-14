package com.netguru.commondata

import com.netguru.commondomain.data.ArDataSource
import com.netguru.commondomain.data.PreferencesDataSource
import com.netguru.commondomain.data.ProductDetailsDataSource
import com.netguru.commondomain.data.ShopDataSource
import com.netguru.commondomain.userContent.UserContentDataSource
import com.russhwolf.settings.Settings
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    single {
        Settings()
    }

    factory {
        ProductDetailsMockDataSource()
    } bind ProductDetailsDataSource::class

    factory {
        ShopMockDataSource()
    } bind ShopDataSource::class

    factory {
        ArMockDataSource()
    } bind ArDataSource::class

    factory {
        UserContentMockDataSource()
    } bind UserContentDataSource::class

    factory {
        PreferencesDataSourceImpl(get())
    } bind PreferencesDataSource::class
}
