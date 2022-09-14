//
//  UserGeneratedSingleImage.swift
//  R&D KMM Retail
//

import SDWebImageSwiftUI
import SwiftUI

/// A custom view that displays the full size of selected user generated image.
struct UserGeneratedSingleImage: View {

    @StateObject var viewModel = UserGeneratedSingleImageViewModel()

    // MARK: - View body

    var body: some View {
        VStack(alignment: .leading) {
            // MARK: - Navigation section

            DetailsNavigationBar(onBackButton: {
                viewModel.handleBack()
            })
                .padding(.top, 50)

            // MARK: - Title section.

            Text(viewModel.productName)
                .font(.retailFont(weight: .bold, size: 24.0))
                .foregroundColor(Color(.common.secondary_dark.uiColor))
                .lineLimit(2)
                .padding()

            // MARK: - Single image full screen section

            ScrollView {
                WebImage(url: URL(string: viewModel.imageUrl))
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .cornerRadius(7)
                    .padding()
                Spacer()
            }
        }
        .background(Color(.common.background_dark.uiColor))
        .ignoresSafeArea()
    }
}
