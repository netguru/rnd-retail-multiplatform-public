package com.netguru.retail.android.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.netguru.retail.android.shared.RetailTopBar
import com.netguru.retail.android.theme.backgroundVariant
import com.netguru.retail.android.theme.setSystemBarColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductDetailsLayout(
    onBackClick: () -> Unit,
    bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    ),
    bottomSheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    setSystemBarColor(
        MaterialTheme.colors.backgroundVariant,
        Color.White
    )

    ModalBottomSheetLayout(
        sheetContent = bottomSheetContent,
        sheetState = bottomSheetState,
        sheetShape = if (bottomSheetState.currentValue == ModalBottomSheetValue.Expanded) {
            RectangleShape
        } else {
            MaterialTheme.shapes.large
        },
        modifier = Modifier.systemBarsPadding()
    ) {
        Scaffold(
            topBar = {
                RetailTopBar(
                    onClickNav = onBackClick,
                    modifier = Modifier.background(
                        color = MaterialTheme.colors.backgroundVariant
                    )
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                content()
            }
        }
    }
}
