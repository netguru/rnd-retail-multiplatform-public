//
//  DetailsNavigationBar.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom view that displays the top navigation section.
struct DetailsNavigationBar: View {

    /// A callback when the back button is pressed.
    let onBackButton: (() -> Void)?

    // MARK: - View body

    var body: some View {
        VStack {
            HStack {
                NavigationButton(icon: .commonAssets.chevron_arrow_left.image) {
                    onBackButton?()
                }
                .padding()
                Spacer()
                NavigationButton(icon: .commonAssets.outlined_shoppingbag_alt.image) {
                    print("Go to cart")
                }
                .padding()
            }
        }
    }
}
