//
//  ThreeDimensionalModel.swift
//  R&D KMM Retail
//

import SwiftUI
import RealityKit
import Combine

/// A 3D model class.
class ThreeDimensionalModel: Equatable {
    /// 3D model's name.
    var name: String

    /// A representation of the physical object to be rendered.
    var modelEntity: ModelEntity?

    /// Scale compensation factor.
    var scaleCompensation: Float

    /// A callback to be executed when cancelled.
    private var cancellable: AnyCancellable?

    /// Default ThreeDimensionalModel initializer.
    init(name: String, scaleCompensation: Float = 1.0) {
        self.name = name
        self.scaleCompensation = scaleCompensation
    }

    /// Loads the 3D model asynchronously.
    func loadModelEntityAsync() {

        cancellable = ModelEntity.loadModelAsync(named: name)
            .sink { [weak self] loadCompletion in
                guard let self = self else { return }

                switch loadCompletion {
                case let .failure(error):
                    print("Failed to load the model for the filename: \(self.name). Error: \(error.localizedDescription)")
                case .finished:
                    break
                }

            } receiveValue: { modelEntity in

                self.modelEntity = modelEntity
                self.modelEntity?.scale *= 100
            }
    }

    static func == (lhs: ThreeDimensionalModel, rhs: ThreeDimensionalModel) -> Bool {
        lhs.name == rhs.name && lhs.modelEntity == rhs.modelEntity
    }
}
