//
//  UserSingleImageServiceMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class UserSingleImageServiceMock: UserSingleImageService {
    func goBack() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func loadUserContent(singleImageRequest: SingleImageRequest) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
