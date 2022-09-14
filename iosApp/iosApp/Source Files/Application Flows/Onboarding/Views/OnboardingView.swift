//
//  OnboardingView.swift
//  R&D KMM Retail
//

import SwiftUI

/// Main onboarding view showing a collection of pages.
struct OnboardingView: View {

    /// A view model.
    @StateObject var viewModel: OnboardingViewModel

    var body: some View {
        VStack(alignment: .leading) {

            // MARK: - Navigation section

            HStack {
                IndicatorView(
                    numberOfItems: viewModel.pages.count,
                    currentIndex: viewModel.currentIndex
                )
                Spacer()
                if !viewModel.isOnLastPage {
                    Button(action: {
                        viewModel.handleSkip()
                    }, label: {
                        Text(String.common.common_skip.localized)
                            .frame(width: 50)
                            .font(.retailFont(weight: .regular, size: 14.0))
                            .foregroundColor(Color(.common.secondary.uiColor))
                    })
                        .buttonStyle(PlainButtonStyle())
                }
            }
            .frame(height: 40)
            .padding()

            // MARK: - Scrollable pages

            TabView(selection: $viewModel.currentIndex) {
                ForEach(0..<viewModel.pages.count, id: \.self) { index in
                    let page = viewModel.pages[index]
                    OnboardingPageView(
                        title: page.title,
                        description: page.description,
                        image: page.image
                    ).tag(index)
                }
            }.tabViewStyle(PageTabViewStyle(indexDisplayMode: .never))

            // MARK: - Next button

            Spacer()
            Button(action: {
                withAnimation {
                    viewModel.isOnLastPage ? viewModel.finishOnboarding() : viewModel.handleNextPage()
                }
            }, label: {
                Text(viewModel.nextButtonTitle)
                    .retailMainButtonText()
            })
                .buttonStyle(PlainButtonStyle())
        }
        .background(Color(UIColor.common.background_dark.uiColor).edgesIgnoringSafeArea(.all))
    }
}

struct OnboardingView_Previews: PreviewProvider {
    static var previews: some View {
        let pages: [OnboardingPage] = [
            OnboardingPage(
                title: .common.onboarding_title_1.localized,
                description: .common.onboarding_message_1.localized,
                image: .commonImages.onboarding_clock.image
            ),
            OnboardingPage(
                title: "Old furniture for your office",
                description: "Finish exploring tomorrow",
                image: .commonImages.onboarding_clock.image
            ),
            OnboardingPage(
                title: "Office in your apartment",
                description: "Stay comfortable",
                image: .commonImages.onboarding_clock.image
            )
        ]
        let viewModel = OnboardingViewModel(
            pages: pages,
            onboardingService: OnboardingServiceMock()
        )
        Group {
            OnboardingView(viewModel: viewModel)

            OnboardingView(viewModel: viewModel)
                .preferredColorScheme(.dark)
        }
    }
}
