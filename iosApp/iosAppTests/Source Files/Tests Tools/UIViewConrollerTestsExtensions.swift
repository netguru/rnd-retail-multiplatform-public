//
//  UIViewConrollerTestsExtensions.swift
//  R&D KMM Retail
//

import UIKit
import Mimus
import SnapshotTesting

@testable import Retail

extension UIViewController {

    func performSnapshotTests(
        named name: String,
        precision: Float = 0.98,
        file: StaticString = #file,
        line: UInt = #line
    ) {
        assertSnapshot(
            matching: self,
            as: .image(on: .iPhoneX, precision: precision),
            named: name,
            file: file,
            testName: "Retail",
            line: line
        )
    }
}
