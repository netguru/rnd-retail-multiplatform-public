//
//  MainCoordinator.swift
//  R&D KMM Retail
//

import Foundation
import UIKit
import common

class MainCoordinator: MainRouter {
    private let navigationController: UINavigationController

    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
        Provider.shared.registerMainRouter(router: self)
    }

    func start() {
        let controller = buildOnboarding()
        navigationController.pushViewController(controller, animated: false)
    }

    func toBottomNav() {
        let controller = buildBottomNav()
        navigationController.setViewControllers([controller], animated: true)
    }

    func toProductDetails() {
        let controller = buildProductDetails()
        navigationController.pushViewController(controller, animated: true)
    }

    func toProductAr() {
        let controller = buildARView()
        navigationController.pushViewController(controller, animated: true)
    }

    func toUserContent() {
        let controller = buildUserGeneratedContentView()
        navigationController.pushViewController(controller, animated: true)
    }

    func toUserSingleImage() {
        let controller = buildUserGeneratedSingleImage()
        navigationController.pushViewController(controller, animated: true)
    }

    func back() {
        navigationController.popViewController(animated: true)
    }

    private func buildOnboarding() -> UIViewController {
        let pages: [OnboardingPage] = [
            OnboardingPage(
                title: .common.onboarding_title_1.localized,
                description: .common.onboarding_message_1.localized,
                image: .commonImages.onboarding_clock.image
            ),
            OnboardingPage(
                title: .common.onboarding_title_2.localized,
                description: .common.onboarding_message_2.localized,
                image: .commonImages.onboarding_chair.image
            ),
            OnboardingPage(
                title: .common.onboarding_title_3.localized,
                description: .common.onboarding_message_3.localized,
                image: .commonImages.chair_placeholder.image
            )
        ]
        let viewModel = OnboardingViewModel(pages: pages)
        return ViewController(view: OnboardingView(viewModel: viewModel))
    }

    private func buildBottomNav() -> UIViewController {
        let shopViewModel = ShopViewModel()
        let shopView = HomeView(viewModel: shopViewModel)
        let shopController = ViewController(view: shopView)
        shopController.tabBarItem = UITabBarItem(
            title: nil,
            image: .commonAssets.outlined_home.image,
            selectedImage: .commonAssets.filled_home.image
        )

        let secondController = UIViewController()
        secondController.view.backgroundColor = .systemGray
        secondController.tabBarItem = UITabBarItem(
            title: nil,
            image: .commonAssets.outlined_apps.image,
            selectedImage: .commonAssets.filled_apps.image
        )

        let thirdController = UIViewController()
        thirdController.view.backgroundColor = .darkGray
        thirdController.tabBarItem = UITabBarItem(
            title: nil,
            image: .commonAssets.outlined_user.image,
            selectedImage: .commonAssets.filled_user.image
        )

        let fourthController = UIViewController()
        fourthController.view.backgroundColor = .lightGray
        fourthController.tabBarItem = UITabBarItem(
            title: nil,
            image: .commonAssets.outlined_shoppingbag.image,
            selectedImage: .commonAssets.filled_shoppingbag.image
        )

        let controllers = [
            shopController,
            secondController,
            thirdController,
            fourthController
        ]
        return BottomNavController(controllers: controllers)
    }

    private func buildProductDetails() -> UIViewController {
        let viewModel = DetailsViewModel()
        let view = DetailsView(viewModel: viewModel)
        let controller = ViewController(view: view)
        return controller
    }

    private func buildARView() -> UIViewController {
        let viewModel = ARViewModel()
        let view = RetailARView(viewModel: viewModel)
        let controller = ViewController(view: view)
        return controller
    }

    private func buildUserGeneratedContentView() -> UIViewController {
        let viewModel = UserGeneratedContentViewModel()
        let view = UserGeneratedContentView(
            viewModel: viewModel
        )
        let controller = ViewController(view: view)
        return controller
    }

    private func buildUserGeneratedSingleImage() -> UIViewController {
        let viewModel = UserGeneratedSingleImageViewModel()
        let view = UserGeneratedSingleImage(
            viewModel: viewModel
        )
        let controller = ViewController(view: view)
        return controller
    }
}
