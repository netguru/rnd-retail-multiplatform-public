package com.netguru.retail.android.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.netguru.commonResources.MR
import com.netguru.retail.android.mocks.OnboardingServiceMock
import com.netguru.retail.android.theme.RetailTheme
import com.netguru.retail.android.theme.backgroundVariant
import com.netguru.retail.android.theme.dimens
import com.netguru.retail.android.theme.setSystemBarColor
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel = viewModel()) {
    val pages = getOnboardingPages()
    val onboardingPagerState = rememberOnboardingPagerState(viewModel)

    setSystemBarColor(
        MaterialTheme.colors.backgroundVariant,
        MaterialTheme.colors.backgroundVariant
    )

    Column(
        Modifier
            .background(MaterialTheme.colors.backgroundVariant)
            .systemBarsPadding()
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.grid_1),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalPagerIndicator(
                pagerState = onboardingPagerState.pagerState,
                activeColor = MaterialTheme.colors.primary,
                indicatorShape = CircleShape,
                modifier = Modifier.padding(MaterialTheme.dimens.grid_2)
            )
            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colors.secondary
                ),
                onClick = { onboardingPagerState.onSkipClick() }
            ) {
                Text(text = stringResource(MR.strings.common_skip))
            }
        }

        HorizontalPager(
            state = onboardingPagerState.pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            count = pages.size
        ) { pageIndex ->
            Page(
                pageData = pages[pageIndex],
                buttonText = onboardingPagerState.getButtonText(),
                onNextClick = { onboardingPagerState.onNextClick() }
            )
        }
    }
}

@Composable
fun getOnboardingPages(): List<PageData> {
    return listOf(
        PageData(
            imageRes = MR.images.onboarding_clock.drawableResId,
            mainText = stringResource(MR.strings.onboarding_title_1),
            subText = stringResource(MR.strings.onboarding_message_1)
        ),
        PageData(
            imageRes = MR.images.onboarding_chair.drawableResId,
            mainText = stringResource(MR.strings.onboarding_title_2),
            subText = stringResource(MR.strings.onboarding_message_2)
        ),
        PageData(
            imageRes = MR.images.chair_placeholder.drawableResId,
            mainText = stringResource(MR.strings.onboarding_title_3),
            subText = stringResource(MR.strings.onboarding_message_3)
        )
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun OnboardingPreview() {
    RetailTheme(darkTheme = false) {
        val viewModel = OnboardingViewModel(
            OnboardingServiceMock()
        )
        OnboardingScreen(viewModel)
    }
}
