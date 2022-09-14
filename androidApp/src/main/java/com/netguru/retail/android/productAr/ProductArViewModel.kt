package com.netguru.retail.android.productAr

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.netguru.commondomain.ar.ArQuery
import com.netguru.commondomain.ar.ArService
import com.netguru.commondomain.base.Loading
import com.netguru.retail.Provider
import com.netguru.retail.android.productAr.TutorialStep.DETECT_PLANE
import com.netguru.retail.android.viewModel.query

object TutorialStep {
    const val DETECT_PLANE = 0
    const val PLACE_MODEL = 1
    const val SELECT_MODEL = 2
    const val MOVE_MODEL = 3
    const val ROTATE_MODEL = 4
}

class ProductArViewModel(
    private val arQuery: ArQuery = Provider.arQuery,
    private val arService: ArService = Provider.arService
) : ViewModel() {

    var arModel by query(arQuery::collectArModel, "")
    var showArOnboarding by query(arQuery::collectShowArOnboarding, false)
    var loadingState by query(arQuery::collectArLoadingState, Loading.InProgress)
    var tutorialStep by mutableStateOf(DETECT_PLANE)
        private set
    var isArModelLoading by mutableStateOf(true)
        private set

    fun handleBack() = arService.goBack()

    fun handleProgressTutorial() {
        tutorialStep++
        if (tutorialStep > TutorialStep.ROTATE_MODEL) arService.finishArOnboarding()
    }

    fun handleModelLoaded() {
        isArModelLoading = false
    }
}
