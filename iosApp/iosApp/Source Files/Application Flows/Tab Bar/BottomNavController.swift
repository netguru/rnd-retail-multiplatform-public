//
//  BottomNavController.swift
//  R&D KMM Retail
//

import UIKit
import common

/// A container for the views in the main part of the app.
class BottomNavController: UITabBarController, UITabBarControllerDelegate {

    // MARK: Private Properties

    private let controllers: [UIViewController]

    // MARK: Initializer

    /// Default initializer
    ///
    /// - Parameter viewModel: A HomeBarControllerViewModel to be supplied.
    init(controllers: [UIViewController]) {
        self.controllers = controllers
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
        setAppearance()
        setupTabBarController()
    }

    // MARK: TabBarController Delegate

    override func tabBar(_ tabBar: UITabBar, didSelect item: UITabBarItem) {
        selectedIndex = tabBar.items?.firstIndex(of: item) ?? 0
    }

    func tabBarController(
        _ tabBarController: UITabBarController,
        shouldSelect viewController: UIViewController
    ) -> Bool {
        false
    }
}

// MARK: Implementation details

private extension BottomNavController {

    func setAppearance() {
        tabBar.tintColor = .black
        if #available(iOS 13.0, *) {
            let tabBarAppearance: UITabBarAppearance = UITabBarAppearance()
            tabBarAppearance.configureWithDefaultBackground()
            tabBarAppearance.backgroundColor = .white
            UITabBar.appearance().standardAppearance = tabBarAppearance

            if #available(iOS 15.0, *) {
                UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
            }
        }
    }

    func setupTabBarController() {
        delegate = self
        viewControllers = controllers
        selectedIndex = 0
    }
}
