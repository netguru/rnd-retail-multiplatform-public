package com.netguru.retail.android.userSingleImage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.netguru.commondomain.userSingleImage.UserSingleImageQuery
import com.netguru.commondomain.userSingleImage.UserSingleImageService
import com.netguru.retail.Provider
import com.netguru.retail.android.viewModel.query

class UserSingleImageViewModel(
    private val userSingleImageService: UserSingleImageService = Provider.userSingleImageService,
    private val userSingleImageQuery: UserSingleImageQuery = Provider.userSingleImageQuery
) : ViewModel() {
    var imageTitle by query(userSingleImageQuery::collectImageTitle, "")
    var imageUrl by query(userSingleImageQuery::collectImageUrl, "")

    fun handleBack() {
        userSingleImageService.goBack()
    }
}
