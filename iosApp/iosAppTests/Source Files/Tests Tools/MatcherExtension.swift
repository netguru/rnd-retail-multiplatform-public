//
//  MatcherExtension.swift
//  R&D KMM Retail
//

import Foundation
import Mimus

@testable import Retail

public extension Matcher where Self: Equatable {

    func matches(argument: Any?) -> Bool {
        compare(other: argument as? Self)
    }
}
