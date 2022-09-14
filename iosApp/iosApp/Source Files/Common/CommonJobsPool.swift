//
//  CommonJobsPool.swift
//  R&D KMM Retail
//

import Foundation
import common
import Combine

typealias CommonJob = Kotlinx_coroutines_coreJob
typealias Collector<T> = (T) -> Void
typealias CollectFunction<T> = (@escaping Collector<T>) -> CommonJob
typealias PublishFunction<T> = (T) -> Void

class CommonJobsPool {
    var jobs: [CommonJob] = []

    func run<T>(collect: @escaping CollectFunction<T>, publish: @escaping PublishFunction<T>) {
        jobs.append(collect { data in
            DispatchQueue.main.async {
                publish(data)
            }
        })
    }

    func cancelAll() {
        jobs.forEach { job in
            job.cancel(cause: nil)
        }
    }
}
