//
//  MainButtonText.swift
//  R&D KMM Retail
//

import SwiftUI

/// A view used for the main button.
struct MainButtonText: ViewModifier {

    /// Sets the height of the view.
    let height: CGFloat

    /// Sets the width of the view.
    let width: CGFloat

    /// Sets the corner radius for the view.
    let cornerRadius: CGFloat

    /// Sets the color used for the background of the view.
    let backgroundColor: Color

    /// Sets the color of the text's font on the view.
    let foregroundColor: Color

    func body(content: Content) -> some View {
        content
            .frame(height: height)
            .frame(maxWidth: width)
            .background(backgroundColor)
            .cornerRadius(cornerRadius)
            .padding()
            .foregroundColor(foregroundColor)
            .font(.retailFont(weight: .regular, size: 18.0))
    }
}

extension View {

    /// Sets the custom appearance of the text for the main button.
    func retailMainButtonText(
        height: CGFloat = 66,
        width: CGFloat = .infinity,
        cornerRadius: CGFloat = 10,
        backgroundColor: Color = Color(.common.primary.uiColor),
        foregroundColor: Color = Color(UIColor.common.secondary_dark.uiColor)
    ) -> some View {
        modifier(
            MainButtonText(
                height: height,
                width: width,
                cornerRadius: cornerRadius,
                backgroundColor: backgroundColor,
                foregroundColor: foregroundColor
            )
        )
    }
}
