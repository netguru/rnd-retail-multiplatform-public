//
//  DetailsSheetView.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom view that displays the description sheet view.
struct DetailsSheetView: View {

    // MARK: - Public properties

    /// A furniture view model.
    let furnitureViewModel: FurnitureViewModel?

    /// A furniture variation view model.
    let selectedFurnitureVaration: FurnitureVariationViewModel?

    /// A details view model.
    @StateObject var detailsViewModel: DetailsViewModel

    // MARK: - Private properties

    @State private var translation: CGSize = .zero

    /// Initial position of the sheet.
    @State private var offsetY: CGFloat = 480

    /// Position of upper snap line. Should be the same as initial offsetY.
    private let upperSnapLine: CGFloat = 480

    /// Position of lower snap line.
    private let lowerSnapLine: CGFloat = 625

    // MARK: - View body

    var body: some View {
        ZStack(alignment: .topLeading) {
            VStack {
                grabber

                VStack(alignment: .leading) {
                    let title = selectedFurnitureVaration?.title ?? ""
                    let description = furnitureViewModel?.description ?? ""
                    titleARandRatingSection(title)
                    descriptionSection(description)
                    customizeButton
                }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .padding(20)
        .background(Color(.common.background.uiColor))
        .cornerRadius(16)
        .offset(y: detailsViewModel.isBottomSheetOpen ? translation.height + offsetY : translation.height + lowerSnapLine)
        .gesture(
            DragGesture()
                .onChanged { value in
                    translation = value.translation
                }
                .onEnded { _ in
                    withAnimation(.interactiveSpring(response: 0.35, dampingFraction: 0.6)) {
                        let snap = translation.height + offsetY
                        if snap > upperSnapLine {
                            offsetY = lowerSnapLine
                        } else {
                            offsetY = upperSnapLine
                        }
                        translation = .zero
                    }
                }
        )
        .ignoresSafeArea()
    }

    private var grabber: some View {
        HStack {
            Spacer()
            RoundedRectangle(cornerRadius: 3)
                .frame(width: 40, height: 3)
                .foregroundColor(Color(.common.secondary.uiColor))
            Spacer()
        }
        .padding(.bottom, 12)
    }

    private func titleARandRatingSection(_ title: String) -> some View {
        HStack(alignment: .top, spacing: 15) {
            VStack(alignment: .leading, spacing: 8) {
                Text(title)
                    .font(.retailFont(weight: .bold, size: 24.0))
                    .foregroundColor(Color(.common.secondary_dark.uiColor))
                    .lineLimit(2)
                HStack {
                    let rating = furnitureViewModel?.averageReviewScore.toDouble() ?? 5.00
                    let reviews = furnitureViewModel?.reviewCount ?? ""
                    Text("\(String(format: "%.1f", rating))")
                        .font(.retailFont(weight: .medium, size: 13.0))
                        .foregroundColor(Color(.common.primary.uiColor))
                    RetailRatingView(rating: rating)
                    Text("(\(reviews) reviews)")
                        .font(.retailFont(weight: .regular, size: 10.0))
                        .foregroundColor(Color(.common.secondary.uiColor))
                }
                .padding(.bottom, 2)
            }
            Spacer()
            Button {
                print("AR View button tapped")
            } label: {
                RectangleButton(color: Color(.common.secondary_medium.uiColor))
            }
        }
    }

    private func descriptionSection(_ description: String) -> some View {
        VStack(alignment: .leading) {
            Text("Description")
                .font(.retailFont(weight: .regular, size: 14.0))
                .foregroundColor(Color(.common.secondary.uiColor))
                .padding([.top, .bottom], 1)
            Text(description)
                .font(.retailFont(weight: .regular, size: 12.0))
                .lineSpacing(5)
                .foregroundColor(Color(.common.secondary.uiColor))
        }
    }

    private var customizeButton: some View {
        HStack {
            ZStack {
                Circle()
                    .foregroundColor(Color(.common.secondary_dark.uiColor))
                    .frame(width: 22, height: 22)

                Circle()
                    .stroke(Color.white, lineWidth: 3)
                    .background(Circle().foregroundColor(Color(.common.primary.uiColor)))
                    .frame(width: 20, height: 20)
            }
            Button {
                print("Customize button tapped")
            } label: {
                Text("Customize")
                    .font(.retailFont(weight: .medium, size: 11.0))
                    .foregroundColor(Color(.common.secondary.uiColor))
                    .underline()
            }
        }
    }
}

struct DetailsSheetView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = DetailsViewModel()
        DetailsSheetView(
            furnitureViewModel: viewModel.furnitureViewModel,
            selectedFurnitureVaration: viewModel.selectedFurnitureVariation,
            detailsViewModel: viewModel
        )
    }
}
