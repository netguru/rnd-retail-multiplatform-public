//
//  Font.swift
//  R&D KMM Retail
//

import commonResources
import SwiftUI

extension Font {
    /// A convinient initializer method to convert a UIFont into the SwiftUI's font.
    init(_ uiFont: UIFont) {
        self = Font(uiFont as CTFont)
    }
}

/// Defines weight of the Retail font.
public enum RetailFontWeight {
    case bold, medium, regular
}

extension Font {
    /// Gets a custom Retail font with a given weight and size.
    public static func retailFont(weight: RetailFontWeight, size: CGFloat) -> Font {
        let mokoFonts = MokoFonts()
        switch weight {
        case .bold:
            return Font(mokoFonts.getFontBold().uiFont(withSize: size))
        case .medium:
            return Font(mokoFonts.getFontMedium().uiFont(withSize: size))
        case .regular:
            return Font(mokoFonts.getFontRegular().uiFont(withSize: size))
        }
    }
}
