//
//  UIImage+Common.swift
//  R&D KMM Retail
//

import UIKit
import SVGKit
import commonResources

/// Easy access to common resources (icons & images)
extension UIImage {

    static var commonAssets: MR.assetsIcons {
        MR.assetsIcons()
    }

    static var commonImages: MR.images {
        MR.images()
    }
}

/// Converts SVG file path from `AssetResource` type to `UIImage`
extension AssetResource {
    var image: UIImage {
        SVGKImage(contentsOf: URL(fileURLWithPath: path)).uiImage
    }
}

extension ImageResource {
    var image: UIImage {
        toUIImage() ?? UIImage()
    }
}
