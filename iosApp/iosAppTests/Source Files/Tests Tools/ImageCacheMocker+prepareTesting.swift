//
//  ImageCacheMocker+prepareTesting.swift
//  R&D KMM Retail
//

import Foundation
import SDWebImageMockPlugin

extension ImageCacheMocker {
    func prepareTesting(for objectName: AnyClass) {
        registerProvider(.bundleImageProvider(
            bundlesWithIdentifiers: [MockImages.mockBundle: Bundle(for: objectName)])
        )
        setupSDWebImageMocking()
    }
}
