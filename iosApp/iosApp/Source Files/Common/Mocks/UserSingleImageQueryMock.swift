//
//  UserSingleImageQueryMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class UserSingleImageQueryMock: UserSingleImageQuery {
    func collectImageTitle(collector: @escaping (String) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectImageUrl(collector: @escaping (String) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
