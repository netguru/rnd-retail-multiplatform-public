//
//  FurnitureVariationsCommonStorageIntent.swift
//  R&D KMM Retail
//

import common
import Foundation

/// An abstraction to call an action to the common furniture shop store.
struct FurnitureVariationsCommonStorageIntent {

    private let store: ShopStore

    /// A default initializer.
    ///
    /// - Parameter store: a shared instance of the store from the common part.
    init(store: ShopStore = Provider.shared.shopStore) {
        self.store = store
    }

    /// Sends an action to load furnitures from the common part of the application.
    func send() {
        store.handle(action: ShopAction.LoadProducts())
    }
}
