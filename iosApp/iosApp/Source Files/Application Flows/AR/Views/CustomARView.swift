//
//  CustomARView.swift
//  R&D KMM Retail
//

import ARKit
import FocusEntity
import RealityKit

/// A custom ARView with a focus object to represent where an object should be placed in a scene.
class CustomARView: ARView {

    /// A visual hint to be used for showing a place to put an object to.
    var focusEntity: FocusEntity?

    var viewModel: ARViewModel

    var isOnboardingStarted = false

    /// Required  CustomARView initializer.
    ///
    /// - Parameter frame: The frame for the view.
    required init(frame frameRect: CGRect, viewModel: ARViewModel) {
        self.viewModel = viewModel
        super.init(frame: frameRect)
        focusEntity = FocusEntity(on: self, focus: .classic)
        setup()
        #if !targetEnvironment(simulator)
            setDelegate()
        #endif
    }

    @MainActor dynamic required init?(coder decoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    @MainActor dynamic required init(frame frameRect: CGRect) {
        fatalError("init(frame:) has not been implemented")
    }

    deinit {
        session.pause()
    }
}

// MARK: Implementation details

extension CustomARView {

    private func setup() {
        let config = ARWorldTrackingConfiguration()
        config.planeDetection = [.horizontal]

        if ARWorldTrackingConfiguration.supportsSceneReconstruction(.mesh) {
            config.sceneReconstruction = .mesh
        }

        if ARWorldTrackingConfiguration.supportsFrameSemantics(.personSegmentationWithDepth) {
            config.frameSemantics.insert(.personSegmentationWithDepth)
        }

        if !environment.sceneUnderstanding.options.contains(.occlusion) {
            environment.sceneUnderstanding.options.insert(.occlusion)
        }

        session.run(config)
    }
}

#if !targetEnvironment(simulator)

    extension CustomARView: FocusEntityDelegate {

        func setDelegate() {
            focusEntity?.delegate = self
            focusEntity?.setAutoUpdate(to: true)
        }

        func toTrackingState() {
            if !isOnboardingStarted {
                isOnboardingStarted = true
                viewModel.handleOnboardingSlideChange()
            }
        }
    }
#endif
