package com.netguru.retail.android.productAr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.netguru.commonResources.MR
import com.netguru.retail.android.theme.RetailTheme

@Composable
fun OnboardingArScreen(
    tutorialStep: Int
) {
    val pages = remember { getAROnboardingPages() }
    if (tutorialStep < pages.size) {
        PageAR(pageData = pages[tutorialStep])
    }
}

fun getAROnboardingPages(): List<PageArData> {
    return listOf(
        PageArData(
            MR.assets.icons.ar_onboarding_step_1,
            MR.strings.ar_onboarding_step_1,
            80.dp,
            80.dp
        ),
        PageArData(
            MR.assets.icons.ar_onboarding_step_2,
            MR.strings.ar_onboarding_step_2,
            200.dp,
            100.dp
        ),
        PageArData(
            MR.assets.icons.ar_onboarding_step_3,
            MR.strings.ar_onboarding_step_3,
            110.dp,
            100.dp
        ),
        PageArData(
            MR.assets.icons.ar_onboarding_step_4,
            MR.strings.ar_onboarding_step_4,
            80.dp,
            80.dp
        ),
        PageArData(
            MR.assets.icons.ar_onboarding_step_5,
            MR.strings.ar_onboarding_step_5,
            80.dp,
            80.dp
        )
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
private fun AROnboardingPreview() {
    RetailTheme(darkTheme = false) {
        OnboardingArScreen(tutorialStep = 0)
    }
}
