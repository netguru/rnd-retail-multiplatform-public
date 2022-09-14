//
//  HomeView.swift
//  R&D KMM Retail
//

import SwiftUI
import common

struct HomeView: View {

    @StateObject var viewModel: ShopViewModel

    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {

            HStack {

                NavigationButton(icon: .commonAssets.menu.image) {
                    print("Menu selected.")
                }
                Spacer()
                NavigationButton(icon: .commonAssets.notifications.image) {
                    print("Notifications selected.")
                }
            }
            .padding()

            SearchBarView(
                placeholderText: .common.desc_search.localized,
                text: viewModel.searchText,
                onTextChange: viewModel.handleSearchProduct
            )
            .padding()

            VStack(alignment: .leading) {
                Text(String.common.recommended.localized)
                    .retailSectionText(foregroundColor: Color(.common.secondary.uiColor))
                RecommendedProductsRow(
                    products: viewModel.recommendedProducts,
                    onProductSelected: viewModel.handleRecommendedProductClick
                )
            }

            VStack(alignment: .leading) {
                Text(String.common.recently_viewed.localized)
                    .retailSectionText(foregroundColor: Color(.common.secondary.uiColor))
                RecentlyViewedProductsList(
                    products: viewModel.recentlyViewedProducts,
                    onProductSelected: viewModel.handleRecentlyViewedProductClick
                )
            }
        }
        .gesture(DragGesture().onChanged { _ in
            UIApplication.shared.endEditing()
        })
        .onTapGesture {
            UIApplication.shared.endEditing()
        }
        .background(Color(UIColor.common.background_dark.uiColor).edgesIgnoringSafeArea(.all))
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = ShopViewModel(
            shopQuery: ShopQueryMock(),
            shopService: ShopServiceMock()
        )
        viewModel.recommendedProducts = [
            RecommendedProduct(id: "1", name: "Whinston", price: Price(currency: "$", amount: 3245.8), thumbnailUrl: "", isNew: true),
            RecommendedProduct(id: "2", name: "Polspoten", price: Price(currency: "$", amount: 2245.2), thumbnailUrl: "", isNew: false)
        ]
        viewModel.recentlyViewedProducts = [
            RecentlyViewedProduct(id: "3", name: "Svenkt", category: "Chair", price: Price(currency: "$", amount: 245.2), thumbnailUrl: ""),
            RecentlyViewedProduct(id: "4", name: "Chem Chair", category: "Chair", price: Price(currency: "$", amount: 142.45), thumbnailUrl: "")
        ]
        return HomeView(viewModel: viewModel)
    }
}
