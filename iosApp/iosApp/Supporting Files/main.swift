//
//  main.swift
//  R&D KMM Retail
//

import UIKit
import common

/// Convenience function to determine if the app is running Unit Tests
private func isRunningTests() -> Bool {
    let environment = ProcessInfo.processInfo.environment
    if environment["XCTestConfigurationFilePath"] != nil {
        return true
    }
    return false
}

/// Empty Application Delegate
/// Used in Unit Tests
class UnitTestsAppDelegate: UIResponder, UIApplicationDelegate {
    override init() {
        Provider.shared.doInit()
    }
}

_ = UIApplicationMain(
    CommandLine.argc,
    CommandLine.unsafeArgv,
    NSStringFromClass(UIApplication.self),
    NSStringFromClass(isRunningTests() ? UnitTestsAppDelegate.self : AppDelegate.self)
)
