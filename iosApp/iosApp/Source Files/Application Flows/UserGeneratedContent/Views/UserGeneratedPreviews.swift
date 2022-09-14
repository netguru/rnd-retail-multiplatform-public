//
//  UserGeneratedPreviews.swift
//  R&D KMM Retail
//

import SDWebImageSwiftUI
import SwiftUI

/// A custom view that displays the horizontal preview gallery of user generated images.
struct UserGeneratedPreviews: View {

    @StateObject var viewModel: DetailsViewModel

    // MARK: - View body

    var body: some View {

        // MARK: - Text section

        HStack {
            Text(String.common.details_posts.localized)
                .font(.retailFont(weight: .regular, size: 14.0))
                .foregroundColor(Color(.common.secondary.uiColor))
            Spacer()

            Text(String.common.details_see_all.localized)
                .font(.retailFont(weight: .medium, size: 11.0))
                .foregroundColor(Color(.common.secondary.uiColor))
                .underline()
                .onTapGesture {
                    viewModel.handleShowAllClick()
                }
        }
        .padding([.leading, .trailing], 25)

        // MARK: - Gallery section

        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(spacing: 10) {
                ForEach(0..<viewModel.userContentImages.count, id: \.self) { index in
                    Button {
                        viewModel.handleShowFullscreenImage(imageUrl: viewModel.userContentImages[index])
                    } label: {
                        WebImage(url: URL(string: viewModel.userContentImages[index]))
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(width: 120, height: 90)
                            .cornerRadius(7)
                    }
                }
            }
            .padding([.leading, .trailing], 25)
        }
    }
}

struct UserGeneratedPreviews_Previews: PreviewProvider {
    static var previews: some View {
        UserGeneratedPreviews(viewModel: DetailsViewModel())
    }
}
