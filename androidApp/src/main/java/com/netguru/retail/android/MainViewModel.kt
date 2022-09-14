package com.netguru.retail.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.main.MainQuery
import com.netguru.commondomain.main.MainService
import com.netguru.retail.Provider
import com.netguru.retail.android.viewModel.query

class MainViewModel(
    private val mainQuery: MainQuery = Provider.mainQuery,
    private val mainService: MainService = Provider.mainService
) : ViewModel() {

    var showOnboarding by query(mainQuery::collectShowOnboarding, false)
    var loading by query(mainQuery::collectLoadingState, Loading.InProgress)

    init {
        mainService.loadShowOnboarding()
    }
}
