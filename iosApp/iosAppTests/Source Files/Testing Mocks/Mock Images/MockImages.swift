//
//  MockImages.swift
//  R&D KMM Retail
//

import Foundation

struct MockImages {

    static let mockBundle = "mockBundle"

    static let mockProductGalleryUrls = [
        URL.imageMock(named: "chair01", inBundleID: mockBundle).absoluteString,
        URL.imageMock(named: "chair02", inBundleID: mockBundle).absoluteString,
        URL.imageMock(named: "chair01", inBundleID: mockBundle).absoluteString
    ]
    static let mockUserContentUrls = [
        URL.imageMock(named: "userContent01", inBundleID: mockBundle).absoluteString,
        URL.imageMock(named: "userContent02", inBundleID: mockBundle).absoluteString,
        URL.imageMock(named: "userContent03", inBundleID: mockBundle).absoluteString
    ]

    static func getTestableImageUrl(forResource resource: String, withExtension extensionString: String) -> String {
        let url = Bundle.main.url(forResource: resource, withExtension: extensionString)
        let stringUrl = url?.absoluteString
        return stringUrl ?? ""
    }
}
