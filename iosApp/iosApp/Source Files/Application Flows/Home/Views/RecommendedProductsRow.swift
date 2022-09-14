//
//  RecommendedProductsRow.swift
//  R&D KMM Retail
//

import SwiftUI
import common

struct RecommendedProductsRow: View {

    let products: [RecommendedProduct]

    let onProductSelected: ((RecommendedProduct) -> Void)?

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(spacing: 24, pinnedViews: .sectionHeaders) {
                ForEach(0..<products.count, id: \.self) { index in
                    let product = products[index]
                    RecommendedProductView(product: product)
                        .onTapGesture {
                            onProductSelected?(product)
                        }
                }
            }
            .padding([.leading, .trailing])
        }
    }
}

struct RecommendedSectionScrollView_Previews: PreviewProvider {
    static var previews: some View {
        RecommendedProductsRow(
            products: [],
            onProductSelected: nil
        )
        .previewLayout(.sizeThatFits)
    }
}
