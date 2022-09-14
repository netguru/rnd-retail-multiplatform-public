//
//  OnboardingPageView.swift
//  R&D KMM Retail
//

import SwiftUI

/// A single onboarding page that can be used to construct the onboarding collection of pages.
struct OnboardingPageView: View {

    /// Title to be shown on a page.
    let title: String

    /// Description to be shown on a page.
    let description: String

    /// Image to be shown on a page.
    let image: UIImage

    var body: some View {
        VStack {
            Image(uiImage: image)
                .resizable()
                .aspectRatio(contentMode: .fit)
            VStack(
                alignment: .leading,
                spacing: 5
            ) {
                Text(title)
                    .font(.retailFont(weight: .bold, size: 24.0))
                    .foregroundColor(Color(UIColor.common.secondary_dark.uiColor))
                HStack {
                    Text(description)
                        .font(.retailFont(weight: .regular, size: 16.0))
                        .foregroundColor(Color(.common.secondary.uiColor))
                    Spacer()
                }
            }
            .padding()
        }
    }
}

struct OnboardingPage_Previews: PreviewProvider {
    static var previews: some View {
        OnboardingPageView(
            title: "Short title",
            description: "Here's the description.",
            image: .commonImages.onboarding_clock.image
        ).previewLayout(.sizeThatFits)
    }
}
