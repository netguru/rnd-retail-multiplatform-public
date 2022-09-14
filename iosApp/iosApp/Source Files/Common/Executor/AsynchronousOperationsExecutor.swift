//
//  AsynchronousOperationsExecutor.swift
//  R&D KMM Retail
//

import Foundation

/// Abstraction for asynchronous operations executor.
/// Executes blocks of code on a predefined execution queue.
protocol AsynchronousOperationsExecutor: AnyObject {

    /// Executor type.
    var type: AsynchronousExecutorType { get }

    /// Executes provided code block on predefined execution queue.
    /// - Parameter block: code block to be executed.
    func execute(_ block: @escaping () -> Void)
}

/// Default OperationsExecutor implementation on the Main Queue.
final class MainQueueOperationsExecutor: AsynchronousOperationsExecutor {

    /// SeeAlso: AsynchronousOperationsExecutor.type
    let type: AsynchronousExecutorType = .main

    /// SeeAlso: AsynchronousOperationsExecutor.execute()
    func execute(_ block: @escaping () -> Void) {
        DispatchQueue.main.async {
            block()
        }
    }
}

/// Default OperationsExecutor implementation using background queue
final class BackgroundQueueOperationsExecutor: AsynchronousOperationsExecutor {

    /// SeeAlso: AsynchronousOperationsExecutor.type
    let type: AsynchronousExecutorType = .background

    /// SeeAlso: AsynchronousOperationsExecutor.execute()
    func execute(_ block: @escaping () -> Void) {
        DispatchQueue(label: "BackgroundQueueOperationsExecutor", attributes: .concurrent).async {
            block()
        }
    }
}

/// An enumeration describing properties of an executor queue.
public enum AsynchronousExecutorType {

    /// Main Queue.
    case main

    /// Background concurrent queue.
    case background
}
