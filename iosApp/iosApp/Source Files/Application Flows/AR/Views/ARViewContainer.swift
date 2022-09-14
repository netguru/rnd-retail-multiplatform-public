//
//  ARViewContainer.swift
//  R&D KMM Retail
//

import SwiftUI
import RealityKit

/// A RealityKit view container to be used in the SwiftUI hierarchy.
struct ARViewContainer: UIViewRepresentable {

    /// A view model.
    @StateObject var viewModel: ARViewModel

    /// Returns an ARView with a zero frame to be set later in the view heirarchy.
    func makeUIView(context: Context) -> some CustomARView {
        let arView = CustomARView(frame: .zero, viewModel: viewModel)

        viewModel.sceneObserver = arView.scene.subscribe(to: SceneEvents.Update.self) { _ in
            self.updateScene(for: arView)
        }

        return arView
    }

    /// Updates an ARView.
    func updateUIView(_ uiView: UIViewType, context: Context) {}
}

// MARK: Implementation details

private extension ARViewContainer {

    func updateScene(for arView: CustomARView) {
        #if !targetEnvironment(simulator)
            arView.focusEntity?.isEnabled = viewModel.selectedModel != nil
        #endif

        if let confirmedModel = viewModel.confirmedModel,
           let modelEntity = confirmedModel.modelEntity {

            viewModel.handlePlaceModel(modelEntity, in: arView)
            viewModel.confirmedModel = nil
        }
    }
}
