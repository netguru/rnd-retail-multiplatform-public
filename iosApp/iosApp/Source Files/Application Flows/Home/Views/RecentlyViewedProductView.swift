//
//  RecentlyViewedProductView.swift
//  R&D KMM Retail
//

import SDWebImageSwiftUI
import SwiftUI
import common

struct RecentlyViewedProductView: View {

    /// A single furniture item to be shown.
    let product: RecentlyViewedProduct

    var body: some View {
        VStack {
            HStack(spacing: 24) {
                let url = product.thumbnailUrl
                WebImage(url: URL(string: url))
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .padding(.leading, 32)
                    .frame(width: 80, height: 80)
                    .background(url.isEmpty ? Color(.common.background_dark.uiColor) : .clear)
                VStack(alignment: .leading) {
                    Text(product.name)
                        .font(.retailFont(weight: .medium, size: 14))
                        .foregroundColor(Color(.common.secondary_dark.uiColor))
                        .lineLimit(2)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text(product.category)
                        .font(.retailFont(weight: .regular, size: 14))
                        .foregroundColor(Color(.common.secondary.uiColor))
                }
                Text(product.price.format())
                    .font(.retailFont(weight: .medium, size: 14))
                    .foregroundColor(Color(.common.secondary_dark.uiColor))
                    .padding(.trailing, 24)
            }
            .frame(height: 88)
        }
    }
}

struct RecentlyViewedFurnitureView_Previews: PreviewProvider {
    static var previews: some View {
        let product = RecentlyViewedProduct(
            id: "1",
            name: "Title",
            category: "Category",
            price: Price(currency: "$", amount: 40.90),
            thumbnailUrl: ""
        )
        RecentlyViewedProductView(product: product)
            .previewLayout(.sizeThatFits)
    }
}
