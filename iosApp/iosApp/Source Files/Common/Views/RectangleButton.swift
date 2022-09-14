//
//  RectangleButton.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom reusable AR view button.
struct RectangleButton: View {

    /// Rectangle foreground color
    let color: Color

    var body: some View {
        ZStack {
            Rectangle()
                .frame(width: 55, height: 50)
                .foregroundColor(color)
                .cornerRadius(4)
            VStack {
                Image(uiImage: .commonAssets.ar_joystick.image)
                Text(String.common.details_ar_view.localized)
                    .foregroundColor(.white)
                    .font(.retailFont(weight: .medium, size: 10.0))
            }
        }
    }
}

struct ARViewButton_Previews: PreviewProvider {
    static var previews: some View {
        RectangleButton(color: Color(.common.secondary_medium.uiColor))
    }
}
