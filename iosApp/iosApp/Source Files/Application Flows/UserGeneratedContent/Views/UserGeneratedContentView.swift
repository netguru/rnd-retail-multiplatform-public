//
//  UserGeneratedContentView.swift
//  R&D KMM Retail
//

import SDWebImageSwiftUI
import SwiftUI

/// A custom view that displays the full gallery of user generated images.
struct UserGeneratedContentView: View {

    @StateObject var viewModel: UserGeneratedContentViewModel

    // MARK: - View body

    var body: some View {
        VStack(alignment: .leading) {
            // MARK: - Navigation section.

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

            // MARK: - column grid with user generated images.

            GeometryReader { g in
                ScrollView {
                    LazyVGrid(
                        columns: [
                            GridItem(.flexible()),
                            GridItem(.flexible()),
                            GridItem(.flexible())
                        ],
                        spacing: 7) {
                        ForEach(viewModel.imageUrls, id: \.self) { url in
                            Button {
                                viewModel.handleShowFullscreenImage(imageUrl: url)
                            } label: {
                                let imageWidth = g.size.width / 3.4
                                let imageHeight = imageWidth
                                WebImage(url: URL(string: url))
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(width: imageWidth, height: imageHeight)
                                    .cornerRadius(7)
                            }
                        }
                        plusButton
                    }
                    .padding()
                }
            }
        }
        .background(Color(.common.background_dark.uiColor))
        .ignoresSafeArea()
    }

    private var plusButton: some View {
        VStack {
            Image(systemName: "plus")
                .frame(width: 25, height: 25)
                .background(Color(.common.secondary.uiColor))
                .foregroundColor(Color(.common.background.uiColor))
                .cornerRadius(5)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .aspectRatio(contentMode: .fill)
        .background(Color(.common.secondary_light.uiColor))
        .cornerRadius(7)
    }
}

struct UserGeneratedContentView_Previews: PreviewProvider {
    static var previews: some View {
        UserGeneratedContentView(
            viewModel: UserGeneratedContentViewModel()
        )
    }
}
