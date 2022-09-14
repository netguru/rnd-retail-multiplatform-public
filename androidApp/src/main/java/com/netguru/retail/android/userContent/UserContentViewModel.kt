package com.netguru.retail.android.userContent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.userContent.UserContentQuery
import com.netguru.commondomain.userContent.UserContentService
import com.netguru.retail.Provider
import com.netguru.retail.android.viewModel.query

class UserContentViewModel(
    private val userContentService: UserContentService = Provider.userContentService,
    private val userContentQuery: UserContentQuery = Provider.userContentQuery
) : ViewModel() {

    var productTitle by query(userContentQuery::collectUserContentTitle, "")
    var images by query(userContentQuery::collectUserContentImages, emptyList())
    var loading by query(userContentQuery::collectLoading, Loading.Idle)

    fun handleUserContentImageClick(imageUrl: String) {
        userContentService.showImageInFullscreen(imageUrl)
    }

    fun handleAddUserContentClick() = Unit

    fun handleBack() {
        userContentService.goBack()
    }
}
