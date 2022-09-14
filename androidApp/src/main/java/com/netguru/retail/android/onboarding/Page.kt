package com.netguru.retail.android.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.RetailPrimaryButton
import com.netguru.retail.android.theme.RetailTheme
import com.netguru.retail.android.theme.dimens
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun Page(
    pageData: PageData,
    buttonText: String,
    onNextClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.dimens.grid_1)
    ) {
        Image(
            painterResource(pageData.imageRes),
            contentDescription = stringResource(MR.strings.desc_onboarding_image),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.5f)
        )
        Text(
            text = pageData.mainText,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.grid_2)
                .padding(top = MaterialTheme.dimens.grid_2)
                .align(Alignment.Start),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h1
        )
        Text(
            text = pageData.subText,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.grid_2)
                .padding(bottom = MaterialTheme.dimens.grid_2, top = MaterialTheme.dimens.grid_1)
                .align(Alignment.Start),
            style = MaterialTheme.typography.body1.copy(fontSize = 16.sp)
        )

        RetailPrimaryButton(
            onClick = onNextClick,
            modifier = Modifier
                .padding(MaterialTheme.dimens.grid_2)
                .padding(bottom = MaterialTheme.dimens.grid_2_5)
                .fillMaxWidth()
        ) {
            Text(
                text = buttonText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun PagePreview() {
    RetailTheme(darkTheme = false) {
        Page(getOnboardingPages()[0], "Continue") {}
    }
}
