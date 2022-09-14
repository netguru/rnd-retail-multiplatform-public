//
//  UIApplication+Editing.swift
//  R&D KMM Retail
//

import SwiftUI

/// Calls to dismiss the keyboard.
///
/// This is a method to support iOS 14 as the currently supported version of this app.
/// It can be deprecated in the future once the app abandons iOS 14 support.
extension UIApplication {
    func endEditing() {
        sendAction(
            #selector(UIResponder.resignFirstResponder),
            to: nil,
            from: nil,
            for: nil
        )
    }
}
