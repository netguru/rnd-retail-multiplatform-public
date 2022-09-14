//
//  UserContentServiceMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class UserContentServiceMock: UserContentService {
    func goBack() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func loadUserContent(userContentRequest: UserContentRequest) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func showImageInFullscreen(imageUrl: String) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
