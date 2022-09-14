//
//  DetailsGalleryCellView.swift
//  R&D KMM Retail
//

import SDWebImageSwiftUI
import SwiftUI

/// A custom view displaying the product gallery view.
struct DetailsGalleryCellView: View {

    let imageURLs: [String]

    @State private var isLoading = true

    var body: some View {
        ZStack {
            VStack {
                if !imageURLs.isEmpty {
                    TabView {
                        ForEach(imageURLs.indices, id: \.self) { i in
                            WebImage(url: URL(string: imageURLs[i]))
                                .resizable()
                                .indicator(.activity)
                                .scaledToFill()
                                .frame(maxWidth: .infinity, alignment: .center)
                        }
                    }
                    .tabViewStyle(PageTabViewStyle())
                    .onAppear {
                        isLoading = false
                    }
                    Spacer()
                }
            }

            if isLoading {
                ProgressIndicator()
            }
        }
    }
}

struct DetailsGalleryCellView_Previews: PreviewProvider {
    static var previews: some View {

        let productsUrls = [
            "https://www.iconbydesign.com.au/assets/full/OW1050-BN.jpg",
            "https://www.iconbydesign.com.au/assets/alt_1/OW1050-BN.jpg",
            "https://www.iconbydesign.com.au/assets/alt_2/OW1050-BN.jpg",
            "https://www.iconbydesign.com.au/assets/alt_3/OW1050-BN.jpg",
            "https://www.iconbydesign.com.au/assets/alt_10/OW1050-BN.jpg"
        ]
        DetailsGalleryCellView(imageURLs: productsUrls)
    }
}
