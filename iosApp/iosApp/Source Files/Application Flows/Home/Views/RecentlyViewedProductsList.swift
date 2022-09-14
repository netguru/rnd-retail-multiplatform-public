//
//  RecentlyViewedProductsList.swift
//  R&D KMM Retail
//

import SwiftUI
import common

struct RecentlyViewedProductsList: View {

    let products: [RecentlyViewedProduct]

    let onProductSelected: ((RecentlyViewedProduct) -> Void)?

    var body: some View {
        LazyVStack(alignment: .leading, spacing: 0) {
            ForEach(0..<products.count, id: \.self) { index in
                Divider()
                let product = products[index]
                RecentlyViewedProductView(product: product)
                    .onTapGesture {
                        onProductSelected?(product)
                    }
            }
        }
    }
}

struct RecentlyViewedFurnituresList_Previews: PreviewProvider {
    static var previews: some View {
        RecentlyViewedProductsList(
            products: [],
            onProductSelected: nil
        )
        .previewLayout(.sizeThatFits)
    }
}
