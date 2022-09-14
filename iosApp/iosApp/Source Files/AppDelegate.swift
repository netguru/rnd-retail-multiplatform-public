//
//  AppDelegate.swift
//  R&D KMM Retail
//

import UIKit
import SwiftUI
import common

final class AppDelegate: UIResponder, UIApplicationDelegate {

    var coordinator: MainCoordinator?
    var window: UIWindow?

    override init() {
        Provider.shared.doInit()
    }

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        let navController = UINavigationController()

        let coordinator = MainCoordinator(navigationController: navController)
        coordinator.start()
        self.coordinator = coordinator

        let window = UIWindow(frame: UIScreen.main.bounds)
        window.rootViewController = navController
        window.makeKeyAndVisible()
        self.window = window

        return true
    }
}
