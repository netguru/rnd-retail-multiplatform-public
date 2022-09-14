//
//  RetailARView.swift
//  R&D KMM Retail
//

import SwiftUI

/// Main AR view.
struct RetailARView: View {

    /// A view model.
    @StateObject var viewModel: ARViewModel

    var body: some View {
        ZStack(alignment: .top) {
            ZStack {
                #if !targetEnvironment(simulator)
                    ARViewContainer(viewModel: viewModel)
                #endif
                if viewModel.shouldShowOnboarding {
                    ZStack {
                        VStack {
                            Image(uiImage: viewModel.currentOnboardingSlide.image ?? UIImage())
                            Text(viewModel.currentOnboardingSlide.message)
                                .foregroundColor(Color.white)
                                .multilineTextAlignment(.center)
                                .font(.retailFont(weight: .regular, size: 16))
                        }
                        .padding()
                    }
                    .onTapGesture {
                        viewModel.handleOnboardingSlideChange()
                    }
                }
            }

            HStack {
                NavigationButton(icon: .commonAssets.chevron_arrow_left.image) {
                    viewModel.confirmedModel = nil
                    viewModel.sceneObserver?.cancel()
                    viewModel.handleBack()
                }
                Spacer()
            }
            .padding(.leading, 20)
            .padding(.top, 60)
        }
        .edgesIgnoringSafeArea(.all)
    }
}
