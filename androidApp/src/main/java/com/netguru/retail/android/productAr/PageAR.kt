package com.netguru.retail.android.productAr

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.SvgIcon
import com.netguru.retail.android.shared.svgResource
import com.netguru.retail.android.theme.RetailTheme
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PageAR(
    pageData: PageArData
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.dimens.grid_1)
    ) {
        SvgIcon(
            modifier = Modifier
                .size(pageData.width, pageData.height)
                .align(Alignment.CenterHorizontally),
            svg = svgResource(pageData.imageAsset),
            contentDescription = stringResource(MR.strings.desc_onboarding_image)
        )
        Text(
            text = stringResource(pageData.textAsset),
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.grid_6)
                .padding(top = MaterialTheme.dimens.grid_2),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
private fun PagePreview() {
    RetailTheme(darkTheme = false) {
        PageAR(getAROnboardingPages()[0])
    }
}
