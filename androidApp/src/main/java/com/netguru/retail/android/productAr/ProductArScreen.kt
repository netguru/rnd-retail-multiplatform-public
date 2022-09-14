package com.netguru.retail.android.productAr

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gorisse.thomas.lifecycle.lifecycle
import com.netguru.commondomain.base.Loading
import com.netguru.retail.android.shared.LoadingAwareView
import com.netguru.retail.android.theme.setSystemBarColor
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.EditableTransform
import io.github.sceneview.ar.node.PlacementMode

@Composable
fun ProductArScreen(
    viewModel: ProductArViewModel = viewModel()
) {
    setSystemBarColor(
        Color.Transparent,
        Color.Transparent,
        false
    )

    val arScreenLifecycle = rememberArScreenLifecycle()
    val modelNode = rememberArModelNode()
    val context = LocalContext.current
    LaunchedEffect(viewModel.loadingState) {
        if (viewModel.loadingState == Loading.Idle) {
            modelNode.loadModelAsync(
                context = context,
                lifecycle = arScreenLifecycle,
                glbFileLocation = viewModel.arModel,
                onLoaded = { viewModel.handleModelLoaded() }
            )
        }
    }

    BackHandler(true) {
        viewModel.handleBack()
    }
    LoadingAwareView(
        isLoading = viewModel.isArModelLoading,
        modifier = Modifier.background(MaterialTheme.colors.onBackground)
    ) {
        AndroidView(
            modifier = Modifier,
            factory = { ProductArSceneView(it, viewModel, modelNode) }
        )
        if (viewModel.showArOnboarding) {
            OnboardingArScreen(viewModel.tutorialStep)
        }
    }
}

// Creates a dummy fragment, which is not visible. It is used only to
// get a lifecycle object tied to the composable calling this function.
@Composable
fun rememberArScreenLifecycle(): Lifecycle? {
    var arScreenLifecycle: Lifecycle? by remember { mutableStateOf(null) }
    AndroidView(
        factory = {
            FragmentContainerView(it).apply { arScreenLifecycle = lifecycle }
        }
    )
    return arScreenLifecycle
}

@Composable
private fun rememberArModelNode() = remember {
    ArModelNode(
        placementMode = PlacementMode.PLANE_HORIZONTAL
    ).apply {
        editableTransforms = setOf(
            EditableTransform.ROTATION,
            EditableTransform.POSITION
        )
    }
}
