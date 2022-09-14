//
//  ProgressIndicator.swift
//  R&D KMM Retail
//

import SwiftUI

/// Simple activity circular indicator (loader) with background
struct ProgressIndicator: View {
    var body: some View {
        ZStack {
            Color(.common.surface.uiColor)
                .ignoresSafeArea()
                .opacity(0.8)

            ProgressView()
                .progressViewStyle(.circular)
                .scaleEffect(2)
        }
    }
}
