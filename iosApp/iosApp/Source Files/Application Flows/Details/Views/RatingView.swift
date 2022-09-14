//
//  RatingView.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom view that displays the rating view.
struct RatingView: View {

    // MARK: - Private properties

    private let maxRating: Double = 5.0
    private let fullCount: Int
    private let emptyCount: Int
    private let halfFullCount: Int
    private let rating: Double

    private var fullStar: some View {
        Image(systemName: "star.fill").foregroundColor(Color(.common.primary.uiColor))
    }

    private var halfFullStar: some View {
        Image(systemName: "star.lefthalf.fill").foregroundColor(Color(.common.primary.uiColor))
    }

    private var emptyStar: some View {
        Image(systemName: "star").foregroundColor(Color(.common.primary.uiColor))
    }

    // MARK: - Initializer

    /// A default Rating view initializer.
    ///
    /// - Parameter rating: the rating of chosen furniture.
    init(
        rating: Double
    ) {
        self.rating = rating
        fullCount = Int(rating)
        emptyCount = Int(maxRating - rating)
        halfFullCount = (Double(fullCount + emptyCount) < maxRating) ? 1 : 0
    }

    // MARK: - View body

    var body: some View {
        HStack(spacing: 0) {
            ForEach(0..<fullCount, id: \.self) { _ in
                self.fullStar
            }
            ForEach(0..<halfFullCount, id: \.self) { _ in
                self.halfFullStar
            }
            ForEach(0..<emptyCount, id: \.self) { _ in
                self.emptyStar
            }
        }
    }
}
