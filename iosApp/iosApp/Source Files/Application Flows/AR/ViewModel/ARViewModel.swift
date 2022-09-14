//
//  ARViewModel.swift
//  R&D KMM Retail
//

import Combine
import common
import RealityKit
import UIKit

/// A view model for the ARView
class ARViewModel: ObservableObject {

    // MARK: - Public Properties

    /// Selected model to be loaded.
    @Published var selectedModel: ThreeDimensionalModel?

    /// Confirmed model to be placed in an AR scene.
    @Published var confirmedModel: ThreeDimensionalModel?

    /// This property retains he cancellable object for the SceneEvents.Update subscriber.
    var sceneObserver: Cancellable?

    /// Indicates whether the onboarding tutorial should be shown.
    @Published var shouldShowOnboarding: Bool

    /// A currently presented onboarding slide.
    @Published var currentOnboardingSlide: AROnboardingSlide

    /// An index of the currently presented onboarding slide.
    @Published var currentOnboardingSlideIndex = 0

    /// Filename string represents selected 3D model name.
    @Published var arModelFilename = ""

    // MARK: - Public Properties for AR Onboarding

    @Published var isOnboardingRotationDone: Bool = false

    @Published var isOnboardingTranslationDone: Bool = false

    let onboardingSlides: [AROnboardingSlide]?

    // MARK: - Private Properties

    private let arQuery: ArQuery

    private let arService: ArService

    private let pool = CommonJobsPool()

    private var arLoadingState: Loading = .InProgress()

    // MARK: Initializer

    /// Initializes a new ARViewModel with and without an option of onboarding.
    ///
    /// - Parameters:
    ///   - shouldShowOnboarding: Indicates whether the onboarding should be shown.
    ///   - onboardingSlides: A collection of the onboarding tutorial slides.
    ///   - arQuery: Observes the state and provides data prepared for displaying. Read business logic.
    ///   - arService: Handles user intents and updates the state accordingly. Write business logic.
    init(
        shouldShowOnboarding: Bool = true,
        onboardingSlides: [AROnboardingSlide]? = AROnboardingSlides().collection,
        arQuery: ArQuery = Provider.shared.arQuery,
        arService: ArService = Provider.shared.arService
    ) {
        self.shouldShowOnboarding = shouldShowOnboarding
        self.onboardingSlides = onboardingSlides
        self.arQuery = arQuery
        self.arService = arService

        currentOnboardingSlide = onboardingSlides?[0] ?? AROnboardingSlide(image: nil, message: "")

        pool.run(collect: arQuery.collectArModel) { [weak self] arModelFilename in
            guard let self = self else { return }

            self.arModelFilename = arModelFilename
        }

        pool.run(collect: arQuery.collectArLoadingState) { [weak self] arLoadingState in
            guard let self = self else { return }

            self.arLoadingState = arLoadingState
            if self.arLoadingState == .Idle() {
                self.selectedModel = ThreeDimensionalModel(name: self.arModelFilename)
                self.selectedModel?.loadModelEntityAsync()
            }
        }
    }

    /// Handles the change of the onborading slides.
    func handleOnboardingSlideChange() {
        if currentOnboardingSlideIndex == 0 {
            goToNextOnboardingSlide()
            HapticFeedback.vibrate(with: .success)

        } else if currentOnboardingSlideIndex == 1 {
            confirmSelectedModel()
            goToNextOnboardingSlide()
            HapticFeedback.vibrate(with: .success)

        } else if isOnboardingTranslationDone, currentOnboardingSlideIndex == 2 {
            goToNextOnboardingSlide()
            HapticFeedback.vibrate(with: .selection)

        } else if isOnboardingRotationDone, currentOnboardingSlideIndex == 3 {
            goToNextOnboardingSlide()
            HapticFeedback.vibrate(with: .selection)

        } else if currentOnboardingSlideIndex == 4 {
            shouldShowOnboarding = false
        }
    }

    /// Place the model on chosen location.
    func confirmSelectedModel() {
        confirmedModel = selectedModel
        selectedModel = nil
    }

    /// Handles back navigation service.
    func handleBack() {
        arService.goBack()
    }

    /// Handles placement of the model entity
    func handlePlaceModel(_ modelEntity: ModelEntity, in arView: ARView) {
        #if !targetEnvironment(simulator)
            let clonedEntity = modelEntity.clone(recursive: true)
            clonedEntity.generateCollisionShapes(recursive: true)

            arView.installGestures([.translation, .rotation], for: clonedEntity).forEach { gestureRecognizer in
                gestureRecognizer.addTarget(self, action: #selector(handleGesture(_:)))
            }

            let anchorEntity = AnchorEntity(plane: .any)
            anchorEntity.addChild(clonedEntity)

            arView.scene.addAnchor(anchorEntity)
        #endif
    }

    @objc private func handleGesture(_ recognizer: UIGestureRecognizer) {
        if recognizer is EntityRotationGestureRecognizer, recognizer.state == .ended {
            isOnboardingRotationDone = true
            handleOnboardingSlideChange()
        }

        if recognizer is EntityTranslationGestureRecognizer, recognizer.state == .ended {
            isOnboardingTranslationDone = true
            handleOnboardingSlideChange()
        }
    }

    private func goToNextOnboardingSlide() {
        guard let onboardingSlides = onboardingSlides else { return }
        guard currentOnboardingSlideIndex < onboardingSlides.count else { return }
        currentOnboardingSlideIndex += 1
        currentOnboardingSlide = onboardingSlides[currentOnboardingSlideIndex]
    }

    deinit {
        pool.cancelAll()
    }
}
