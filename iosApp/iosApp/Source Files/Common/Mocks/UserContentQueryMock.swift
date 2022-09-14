//
//  UserContentQueryMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class UserContentQueryMock: UserContentQuery {
    func collectLoading(collector: @escaping (Loading) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectUserContentImages(collector: @escaping ([String]) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectUserContentTitle(collector: @escaping (String) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
