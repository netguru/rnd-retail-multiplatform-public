package com.netguru.retail.android.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.netguru.commonResources.MR
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
class OnboardingPagerState(
    val pagerState: PagerState,
    private val onboardingViewModel: OnboardingViewModel,
    private val scope: CoroutineScope
) {
    @Composable
    fun getButtonText() = if (isLastPage) {
        stringResource(MR.strings.common_finish)
    } else {
        stringResource(MR.strings.common_continue)
    }

    fun onNextClick() {
        scope.launch {
            if (isLastPage) {
                onboardingViewModel.handleFinishOnboarding()
            } else {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
    fun onSkipClick() {
        scope.launch {
            onboardingViewModel.handleFinishOnboarding()
        }
    }

    private val isLastPage
        get() = pagerState.currentPage == pagerState.pageCount - 1
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun rememberOnboardingPagerState(
    onboardingViewModel: OnboardingViewModel,
    pagerState: PagerState = rememberPagerState(),
    scope: CoroutineScope = rememberCoroutineScope()
) = remember {
    OnboardingPagerState(pagerState, onboardingViewModel, scope)
}
