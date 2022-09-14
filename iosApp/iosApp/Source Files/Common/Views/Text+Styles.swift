//
//  Text+Styles.swift
//  R&D KMM Retail
//

import SwiftUI

extension Text {

    /// Creates a rounded capsule-style text label.
    func retailCapsuleStyleText() -> some View {
        frame(alignment: .topTrailing)
            .font(.retailFont(weight: .medium, size: 10.0))
            .foregroundColor(Color(.common.secondary_dark.uiColor))
            .padding(.init(top: 3, leading: 6, bottom: 3, trailing: 6))
            .background(Color(.common.primary.uiColor))
            .cornerRadius(6.0)
    }
}
