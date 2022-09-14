package com.netguru.retail.android.productAr

import android.content.Context
import android.view.MotionEvent
import com.google.ar.core.HitResult
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.arcore.ArFrame
import io.github.sceneview.ar.arcore.LightEstimationMode
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import kotlin.math.pow
import kotlin.math.sqrt

private const val REQUIRED_MOVED_DISTANCE = 0.5f

class ProductArSceneView(
    context: Context,
    private val viewModel: ProductArViewModel,
    private val modelNode: ArModelNode
) : ArSceneView(context) {
    private var planeHasBeenDetected = false

    private var lastPosition: Position? = null
    private var totalDistanceMoved: Float = 0f

    private var lastRotation: Rotation? = null

    init {
        instructions.enabled = false
        planeRenderer.isVisible = true
        lightEstimationMode = LightEstimationMode.ENVIRONMENTAL_HDR
    }

    override fun onTapAr(hitResult: HitResult, motionEvent: MotionEvent) {
        if (viewModel.tutorialStep == TutorialStep.PLACE_MODEL) {
            viewModel.handleProgressTutorial()
        }
        if (planeHasBeenDetected) {
            if (!children.contains(modelNode)) {
                addChild(modelNode)
            }
            val anchor = hitResult.createAnchor()
            modelNode.anchor = anchor
            planeRenderer.isVisible = false
        }
    }

    override fun onArFrame(arFrame: ArFrame) = with(viewModel) {
        if (arFrame.isTrackingPlane) {
            planeHasBeenDetected = true
        }

        if (tutorialStep > TutorialStep.ROTATE_MODEL) return
        when (tutorialStep) {
            TutorialStep.DETECT_PLANE -> if (arFrame.isTrackingPlane) {
                viewModel.handleProgressTutorial()
            }
            TutorialStep.SELECT_MODEL -> if (gestureDetector.selectedNode != null) {
                viewModel.handleProgressTutorial()
            }
            TutorialStep.MOVE_MODEL -> progressTutorialIfModelMovedEnough()
            TutorialStep.ROTATE_MODEL -> progressTutorialIfModelRotated()
        }
    }

    override fun onArSessionFailed(exception: Exception) {
        // If AR is not available or the camara permission has been denied, we add the model
        // directly to the scene for a fallback 3D only usage
        modelNode.centerModel(origin = Position(x = 0.0f, y = 0.0f, z = 0.0f))
        modelNode.scaleModel(units = 1.0f)
        addChild(modelNode)
    }

    private fun progressTutorialIfModelRotated() {
        val newRotation = modelNode.modelRotation
        if (lastRotation != null && newRotation != lastRotation) {
            viewModel.handleProgressTutorial()
        }
        lastRotation = modelNode.modelRotation
    }

    private fun progressTutorialIfModelMovedEnough() {
        val newPosition = modelNode.position
        lastPosition?.let { lastPos ->
            totalDistanceMoved += sqrt(
                (newPosition.x - lastPos.x).pow(2) +
                    (newPosition.y - lastPos.y).pow(2) +
                    (newPosition.z - lastPos.z).pow(2)
            )
        }
        if (totalDistanceMoved > REQUIRED_MOVED_DISTANCE) {
            viewModel.handleProgressTutorial()
        }
        lastPosition = modelNode.position
    }
}
