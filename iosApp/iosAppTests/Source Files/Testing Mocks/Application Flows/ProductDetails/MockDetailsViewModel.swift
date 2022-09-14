//
//  MockDetailsViewModel.swift
//  R&D KMM Retail
//

import common
import Foundation

@testable import Retail

final class MockDetailsViewModel: DetailsViewModel {

    func mockCompleteProduct() {

        gallery = MockImages.mockProductGalleryUrls
        userContentImages = MockImages.mockUserContentUrls

        productInfo = ProductInfo(
            name: "Starcraft 2",
            price: Price(currency: "$", amount: 1234),
            description: "StarCraft II is a sequel to the PC based Real Time Strategy game StarCraft: Brood War made by Blizzard Entertainment. It is split into three installments: the base game with the subtitle Wings of Liberty, and two expansion packs, Heart of the Swarm and Legacy of the Void. StarCraft II features the return of the three species from the original game: Protoss, Terran, and Zerg.",
            averageStars: 4.0,
            numOfReviews: 55
        )

        customizationInfo = CustomizationInfo(
            availableFabrics: [
                FabricVariantInfo(
                    colorName: "Grey",
                    colorCode: 4286477936,
                    selectable: true),
                FabricVariantInfo(
                    colorName: "Black",
                    colorCode: 4280821800,
                    selectable: true),
                FabricVariantInfo(
                    colorName: "Green",
                    colorCode: 4282226279,
                    selectable: true)
            ],
            selectedFabric:
            FabricVariant(
                colorName: "Grey",
                colorCode: 4286477936),
            availableWoods: [
                WoodVariantInfo(
                    colorName: "Beige",
                    colorCode: 4291541145,
                    selectable: true),
                WoodVariantInfo(
                    colorName: "Dark",
                    colorCode: 4284891697,
                    selectable: true),
                WoodVariantInfo(
                    colorName: "Orange",
                    colorCode: 4289954883,
                    selectable: true)
            ],
            selectedWoodName: "Beige",
            availableSizes: [
                SizeVariantInfo(
                    sizeName: "30x20x40",
                    selectable: true),
                SizeVariantInfo(
                    sizeName: "50x30x60",
                    selectable: true)
            ],
            selectedSizeName: "30x20x40",
            numOfSelectedCustomizations: 0
        )

        shoppingCartInfo = ShoppingCartInfo(
            numOfItemsInCart: 1,
            totalPrice: Price(currency: "€", amount: 44.44),
            canDecrement: false,
            canIncrement: true)
    }

    func mockLongPrice() {
        gallery = MockImages.mockProductGalleryUrls
        userContentImages = MockImages.mockUserContentUrls
        shoppingCartInfo = ShoppingCartInfo(
            numOfItemsInCart: 1,
            totalPrice: Price(currency: "$", amount: 224523.2),
            canDecrement: false,
            canIncrement: true)
    }

    func mockHundredItems() {
        gallery = MockImages.mockProductGalleryUrls
        userContentImages = MockImages.mockUserContentUrls
        shoppingCartInfo = ShoppingCartInfo(
            numOfItemsInCart: 100,
            totalPrice: Price(currency: "€", amount: 21.37),
            canDecrement: false,
            canIncrement: true)
    }

    func mockFiveStarsRating() {
        gallery = MockImages.mockProductGalleryUrls
        userContentImages = MockImages.mockUserContentUrls
        productInfo = ProductInfo(
            name: "Marines",
            price: Price(currency: "$", amount: 1234),
            description: "Marines are the all-purpose infantry unit produced from a Barracks. Having the quickest and cheapest production of all Terran units make the Mineral backbone that scales very well with Stimpack, Engineering Bay upgrades and Medivacs from the Starport. Before Stimpack, Marines are less than effective against Zerglings and Zealots; at least one Bunker and Supply Depots to wall-off is recommended for the early game.",
            averageStars: 5.0,
            numOfReviews: 55
        )
    }

    func mockZeroStarsRating() {
        gallery = MockImages.mockProductGalleryUrls
        userContentImages = MockImages.mockUserContentUrls
        productInfo = ProductInfo(
            name: "Ghost",
            price: Price(currency: "€", amount: 12.34),
            description: "The Ghost is a specialized infantry unit built from a Barracks with an attached Tech Lab once a Ghost Academy has been constructed. The Ghost stands as an Anti-Spellcaster unit and high damage versus Light units.",
            averageStars: 0.0,
            numOfReviews: 55
        )
    }

    func mockZeroHalfStarsRating() {
        gallery = MockImages.mockProductGalleryUrls
        userContentImages = MockImages.mockUserContentUrls
        productInfo = ProductInfo(
            name: "Siege Tank",
            price: Price(currency: "$", amount: 1234),
            description: "The long-ranged Siege Tank is a Mechanical unit with high damage, particularly versus Armored like Roaches and Stalkers. Against masses of smaller units, the Siege Tank can switch to the stationary Siege Mode to deal splash damage from longer range. It is built from a Factory with an attached Tech Lab.",
            averageStars: 0.5,
            numOfReviews: 55
        )
    }

    func mockShortDescription() {
        gallery = MockImages.mockProductGalleryUrls
        userContentImages = MockImages.mockUserContentUrls
        productInfo = ProductInfo(
            name: "Hydralisk",
            price: Price(currency: "€", amount: 4321.99),
            description: "The Hydralisk is a ranged Lair-tech unit...",
            averageStars: 4.0,
            numOfReviews: 25
        )
    }

    func mockBottomSheetOpen() {
        detailsModalShowing = true
    }

    func mockBottomSheetOpenWithOptions() {
        customizationInfo = CustomizationInfo(
            availableFabrics: [
                FabricVariantInfo(
                    colorName: "Grey",
                    colorCode: 4286477936,
                    selectable: true),
                FabricVariantInfo(
                    colorName: "Black",
                    colorCode: 4280821800,
                    selectable: true),
                FabricVariantInfo(
                    colorName: "Green",
                    colorCode: 4282226279,
                    selectable: true)
            ],
            selectedFabric:
            FabricVariant(
                colorName: "Green",
                colorCode: 4282226279),
            availableWoods: [
                WoodVariantInfo(
                    colorName: "Beige",
                    colorCode: 4291541145,
                    selectable: true),
                WoodVariantInfo(
                    colorName: "Orange",
                    colorCode: 4289954883,
                    selectable: true)
            ],
            selectedWoodName: "Orange",
            availableSizes: [
                SizeVariantInfo(
                    sizeName: "30x20x40",
                    selectable: true),
                SizeVariantInfo(
                    sizeName: "50x30x60",
                    selectable: true)
            ],
            selectedSizeName: "30x20x40",
            numOfSelectedCustomizations: 2
        )

        detailsModalShowing = true
    }
}
