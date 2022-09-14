//
//  IndicatorView.swift
//  R&D KMM Retail
//

import SwiftUI

/// Reusable indicator component.
struct IndicatorView: View {

    /// Number items in a collection.
    let numberOfItems: Int

    /// Index of the currently selected item in the collection to show the current state.
    let currentIndex: Int

    var body: some View {
        HStack {
            ForEach(0..<numberOfItems, id: \.self) { index in
                if index == currentIndex {
                    Rectangle()
                        .frame(width: 24, height: 6)
                        .cornerRadius(3)
                        .foregroundColor(Color(.common.primary.uiColor))
                } else {
                    Circle()
                        .frame(width: 6, height: 6)
                        .foregroundColor(Color(.common.primary.uiColor))
                }
            }
        }
    }
}
