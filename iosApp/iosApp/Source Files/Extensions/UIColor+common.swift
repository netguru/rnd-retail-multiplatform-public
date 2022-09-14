//
//  UIColor+common.swift
//  R&D KMM Retail
//

import SwiftUI
import commonResources

extension UIColor {

    /// Gets the `ColorResource` objects from the Common Resources.
    static var common: MR.colors {
        MR.colors()
    }
}

extension ColorResource.Single {

    /// Transfroms a `ColorResource` object into a UIColor.
    var uiColor: UIColor {
        color.toUIColor()
    }
}
