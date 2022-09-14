//
//  Formatting.swift
//  R&D KMM Retail
//

import Foundation
import common

extension Price {

    func format() -> String {
        "\(currency)\(amount)"
    }
}
