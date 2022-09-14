//
//  FakeAsynchronousOperationsExecutor.swift
//  R&D KMM Retail
//

import Foundation
import Mimus

@testable import Retail

final class FakeAsynchronousOperationsExecutor: AsynchronousOperationsExecutor, Mock {
    let type: AsynchronousExecutorType = .main
    var storage = Mimus.Storage()

    func execute(_ block: @escaping () -> Void) {
        block()
    }
}
