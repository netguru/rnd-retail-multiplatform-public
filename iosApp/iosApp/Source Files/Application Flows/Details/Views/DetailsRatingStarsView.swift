//
//  DetailsRatingStarsView.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom view displaying the rating value.
struct DetailsRatingStarsView: View {

    // MARK: - Private properties

    /// Product rating value.
    private let rating: Float

    /// Max value of product rating.
    private let maxRating: Float = 5.0

    /// Technical variables casted from `Float` to `Int` needed to calculate amount of stars.
    private let fullCount: Int
    private let emptyCount: Int
    private let halfFullCount: Int

    /// Few handy computed properties used as a façade for longer expressions.
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
        rating: Float
    ) {
        self.rating = rating
        fullCount = Int(rating)
        emptyCount = Int(maxRating - rating)
        halfFullCount = (Float(fullCount + emptyCount) < maxRating) ? 1 : 0
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
