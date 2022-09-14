//
//  Testing.swift
//  R&D KMM Retail
//

import Foundation

/// Helper for UI testing.
struct Testing {

    /// Boolean value to determine whether app is in testing environment.
    static var isAppBeingTested: Bool {
        if ProcessInfo.processInfo.environment["XCTestConfigurationFilePath"] != nil {
            return true
        } else {
            return false
        }
    }
}
