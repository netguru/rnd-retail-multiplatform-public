//
//  String+Common.swift
//  R&D KMM Retail
//

import commonResources

extension String {
    /// Gets the `StringResource` objects from the Common Resources.
    static var common: MR.strings {
        MR.strings()
    }
}

extension StringResource {
    /// Transfroms a `StringResource` object into a String.
    var localized: String {
        desc().localized()
    }
}
