//
//  SectionTitleText.swift
//  R&D KMM Retail
//

import SwiftUI

/// A modifier to be used for styling sections text.
struct SectionTitleText: ViewModifier {

    /// Sets the color of the text's font on the view.
    let foregroundColor: Color

    /// Sets the text's font size.
    let fontSize: CGFloat

    func body(content: Content) -> some View {
        content
            .foregroundColor(foregroundColor)
            .font(.retailFont(weight: .medium, size: fontSize))
            .padding()
    }
}

extension View {

    /// Sets  custom appearance for the section title.
    func retailSectionText(
        foregroundColor: Color = .black
    ) -> some View {
        modifier(
            SectionTitleText(
                foregroundColor: foregroundColor,
                fontSize: 16
            )
        )
    }
}
