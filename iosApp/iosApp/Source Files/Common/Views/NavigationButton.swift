//
//  NavigationButton.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom implementation of a navigation button to be used across various screens.
struct NavigationButton: View {

    /// An icon to indicate the button.
    let icon: UIImage

    /// An action to be trigerred when the button is pressed.
    let action: () -> Void

    var body: some View {
        Button {
            action()
        } label: {
            Image(uiImage: icon)
                .resizable()
                .frame(width: 24, height: 24)
        }
        .buttonStyle(.plain)
    }
}

struct NavigationButton_Previews: PreviewProvider {
    static var previews: some View {
        NavigationButton(icon: .commonAssets.notifications.image) {
            print("Nothing happens")
        }
        .previewLayout(.sizeThatFits)
    }
}
