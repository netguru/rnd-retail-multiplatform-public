//
//  DetailsCustomizationView.swift
//  R&D KMM Retail
//

import HalfASheet
import SwiftUI
import commonResources

/// A bottom sheet displaying customization options.
struct DetailsCustomizationView: View {

    @ObservedObject var viewModel: DetailsViewModel

    @Binding var isShowing: Bool

    // MARK: - View body

    var body: some View {
        HalfASheet(isPresented: $isShowing) {
            VStack(alignment: .leading) {

                drawGrabber()

                Text(String.common.customization_title.localized)
                    .font(.retailFont(weight: .bold, size: 24.0))
                    .foregroundColor(Color(.common.secondary_dark.uiColor))
                    .lineLimit(2)
                    .padding(.bottom, 10)

                buildFabricSection()

                buildWoodSection()

                buildSizeSection()

                Spacer()

                Button {
                    viewModel.handleReset()
                } label: {
                    let customizationResetString = MokoStrings.shared.getFormattedString(
                        stringResource: String.common.customization_reset,
                        input: String(viewModel.customizationInfo.numOfSelectedCustomizations)
                    )
                    Text(customizationResetString.localized())
                        .font(.retailFont(weight: .regular, size: 14.0))
                        .underline()
                        .foregroundColor(Color(.common.secondary_dark.uiColor))
                        .lineLimit(1)
                        .padding(.bottom, 10)
                }
            }
        }
        .height(.proportional(0.45))
        .closeButtonColor(.common.secondary_light.uiColor)
        .backgroundColor(.white)
        .contentInsets(EdgeInsets(top: 10, leading: 25, bottom: 30, trailing: 25))
    }

    // MARK: - Private Methods

    private func buildFabricSection() -> some View {
        VStack(alignment: .leading) {
            Text(String.common.customization_fabric.localized)
                .font(.retailFont(weight: .regular, size: 14.0))
                .foregroundColor(Color(.common.secondary.uiColor))
                .lineLimit(1)

            HStack {
                let availableFabrics = viewModel.customizationInfo.availableFabrics
                let selectedFabric = viewModel.customizationInfo.selectedFabric
                ForEach(0..<availableFabrics.count, id: \.self) { index in
                    if availableFabrics[index].selectable {
                        Button {
                            viewModel.handleFabricCustomize(variantName: availableFabrics[index].colorName)
                        } label: {
                            CustomizeCircle(
                                color: availableFabrics[index].colorCode,
                                isSelected: selectedFabric.colorName == availableFabrics[index].colorName
                            )
                        }
                        .transition(.asymmetric(
                            insertion: .opacity,
                            removal: .opacity.animation(.linear(duration: 0.1)))
                        )
                    }
                }
            }
            .padding(.bottom, 15)
        }
    }

    private func buildWoodSection() -> some View {
        VStack(alignment: .leading) {
            Text(String.common.customization_wood.localized)
                .font(.retailFont(weight: .regular, size: 14.0))
                .foregroundColor(Color(.common.secondary.uiColor))
                .lineLimit(1)

            HStack {
                let availableWoods = viewModel.customizationInfo.availableWoods
                let selectedWood = viewModel.customizationInfo.selectedWoodName
                ForEach(0..<availableWoods.count, id: \.self) { index in
                    if availableWoods[index].selectable {
                        Button {
                            viewModel.handleWoodCustomize(variantName: availableWoods[index].colorName)
                        } label: {
                            CustomizeCircle(
                                color: availableWoods[index].colorCode,
                                isSelected: selectedWood == availableWoods[index].colorName
                            )
                        }
                        .transition(.asymmetric(
                            insertion: .opacity,
                            removal: .opacity.animation(.linear(duration: 0.1)))
                        )
                    }
                }
            }
            .padding(.bottom, 15)
        }
    }

    private func buildSizeSection() -> some View {
        VStack(alignment: .leading) {
            Text(String.common.customization_size.localized)
                .font(.retailFont(weight: .regular, size: 14.0))
                .foregroundColor(Color(.common.secondary.uiColor))
                .lineLimit(1)

            VStack {
                let availableSizes = viewModel.customizationInfo.availableSizes
                let selectedSize = viewModel.customizationInfo.selectedSizeName
                ForEach(0..<availableSizes.count, id: \.self) { index in
                    if availableSizes[index].selectable {
                        Button {
                            viewModel.handleSizeCustomize(variantName: availableSizes[index].sizeName)
                        } label: {
                            CustomizeLabeledCircle(
                                text: availableSizes[index].sizeName,
                                isSelected: selectedSize == availableSizes[index].sizeName
                            )
                        }
                        .transition(.asymmetric(
                            insertion: .opacity,
                            removal: .opacity.animation(.linear(duration: 0.1)))
                        )
                    }
                }
            }
        }
    }

    private func drawGrabber() -> some View {
        HStack {
            Spacer()
            RoundedRectangle(cornerRadius: 3)
                .frame(width: 40, height: 3)
                .foregroundColor(Color(.common.secondary.uiColor))
            Spacer()
        }
        .padding(.bottom, 12)
    }
}
