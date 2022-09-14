//
//  DetailsView.swift
//  R&D KMM Retail
//

import SwiftUI

/// A  view presenting the details screen.
struct DetailsView: View {

    @StateObject var viewModel: DetailsViewModel

    // MARK: - View body

    var body: some View {
        ZStack {
            VStack {

                // MARK: - Navigation section

                DetailsNavigationBar(onBackButton: viewModel.handleBack)
                    .padding(.top, 50)

                ScrollView(showsIndicators: false) {
                    ZStack {
                        VStack {
                            // MARK: - Gallery section

                            DetailsGalleryCellView(imageURLs: viewModel.gallery)
                                .frame(height: 350)

                            // MARK: - Title, description, rating & customization section

                            VStack(alignment: .leading) {
                                let title = viewModel.productInfo.name
                                let description = viewModel.productInfo.description_

                                buildTitleARButtonAndRatingSection(title)

                                buildDescriptionSection(description)

                                buildCustomizationSection()
                            }
                            .padding(25)

                            // MARK: - User generated gallery section

                            UserGeneratedPreviews(
                                viewModel: viewModel
                            )
                        }
                    }
                }

                // MARK: - Add to cart & counter section

                DetailsBottomBar(viewModel: viewModel)
                    .frame(height: 100)
                    .background(Color(.common.background.uiColor))
            }

            // MARK: - Bottom sheet details customization view

            DetailsCustomizationView(viewModel: viewModel, isShowing: $viewModel.detailsModalShowing)
                .transaction { transaction in
                    Testing.isAppBeingTested ? transaction.animation = nil : nil
                }
        }
        .background(Color(.common.background_dark.uiColor))
        .ignoresSafeArea()
    }

    // MARK: - Private methods & properties

    private func buildTitleARButtonAndRatingSection(_ title: String) -> some View {
        HStack(alignment: .top, spacing: 15) {
            VStack(alignment: .leading, spacing: 8) {
                Text(title)
                    .font(.retailFont(weight: .bold, size: 24.0))
                    .foregroundColor(Color(.common.secondary_dark.uiColor))
                    .lineLimit(2)
                HStack {
                    let rating = viewModel.productInfo.averageStars
                    let reviews = viewModel.productInfo.numOfReviews
                    Text("\(String(format: "%.1f", rating))")
                        .font(.retailFont(weight: .medium, size: 13.0))
                        .foregroundColor(Color(.common.primary.uiColor))
                    DetailsRatingStarsView(rating: rating)
                    Text("(\(reviews) reviews)")
                        .font(.retailFont(weight: .regular, size: 10.0))
                        .foregroundColor(Color(.common.secondary.uiColor))
                }
                .padding(.bottom, 2)
            }
            Spacer()
            RectangleButton(color: Color(.common.secondary_medium.uiColor))
                .onTapGesture {
                    viewModel.handleARViewClick()
                }
        }
    }

    private func buildDescriptionSection(_ description: String) -> some View {
        VStack(alignment: .leading) {
            Text(String.common.details_description.localized)
                .font(.retailFont(weight: .regular, size: 14.0))
                .foregroundColor(Color(.common.secondary.uiColor))
                .padding([.top, .bottom], 1)
            Text(description)
                .font(.retailFont(weight: .regular, size: 12.0))
                .lineSpacing(5)
                .foregroundColor(Color(.common.secondary.uiColor))
        }
    }

    private func buildCustomizationSection() -> some View {
        HStack {
            Button {
                viewModel.detailsModalShowing.toggle()
            } label: {
                CustomizeCircle(color: viewModel.customizationInfo.selectedFabric.colorCode, isSelected: true)
                Text(String.common.details_customize.localized)
                    .font(.retailFont(weight: .medium, size: 11.0))
                    .foregroundColor(Color(.common.secondary.uiColor))
                    .underline()
            }
        }
    }
}

struct DetailsView_Previews: PreviewProvider {
    static var previews: some View {
        DetailsView(
            viewModel: DetailsViewModel()
        )
    }
}
