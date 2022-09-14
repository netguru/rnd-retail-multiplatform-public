//
//  RecommendedProductView.swift
//  R&D KMM Retail
//

import SDWebImageSwiftUI
import SwiftUI
import common

/// A card view showing a piece of furniture.
/// - SeeAlso: RecommendedFurnituresHorizontalScrollView
struct RecommendedProductView: View {

    /// A single furniture item to be shown.
    let product: RecommendedProduct

    var body: some View {
        VStack(alignment: .leading) {

            // MARK: Title section

            HStack(alignment: .top, spacing: 12) {
                Text(product.name)
                    .font(.retailFont(weight: .bold, size: 24))
                    .foregroundColor(Color(.common.secondary_dark.uiColor))
                    .lineLimit(2)
                Spacer()
                if product.isNew {
                    Text(String.common.product_new.localized.uppercased())
                        .retailCapsuleStyleText()
                }
            }
            .frame(maxWidth: .infinity)

            // MARK: Price

            Text(product.price.format())
                .font(.retailFont(weight: .regular, size: 14.0))
                .foregroundColor(Color(.common.secondary.uiColor))

            // MARK: Image

            Spacer()
            let url = product.thumbnailUrl
            WebImage(url: URL(string: url))
                .resizable()
                .indicator(.activity)
                .scaledToFit()
                .frame(maxWidth: .infinity, alignment: .center)
                .background(url.isEmpty ? Color(.common.background_dark.uiColor) : .clear)
        }
        .padding()
        .frame(width: 280.0, height: 330.0)
        .cornerRadius(16.0)
        .overlay(
            RoundedRectangle(cornerRadius: 16)
                .stroke(Color(.common.secondary.uiColor), lineWidth: 0.5)
        )
    }
}

struct RecommendedFurnitureView_Previews: PreviewProvider {
    static var previews: some View {
        let product = RecommendedProduct(
            id: "1",
            name: "Title",
            price: Price(currency: "$", amount: 39.90),
            thumbnailUrl: "",
            isNew: true
        )
        Group {
            RecommendedProductView(product: product)
                .previewLayout(.sizeThatFits)
            RecommendedProductView(product: product)
                .previewLayout(.sizeThatFits)
                .preferredColorScheme(.dark)
        }
    }
}
