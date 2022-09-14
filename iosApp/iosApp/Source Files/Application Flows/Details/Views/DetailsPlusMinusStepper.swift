//
//  DetailsPlusMinusStepper.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom view that displays stepper -/+ buttons.
struct DetailsPlusMinusStepper: View {

    @StateObject var viewModel: DetailsViewModel

    // MARK: - View body

    var body: some View {
        HStack {
            ZStack {
                let canIncrement = viewModel.shoppingCartInfo.canIncrement
                let canDecrement = viewModel.shoppingCartInfo.canDecrement
                RoundedRectangle(cornerRadius: 10)
                    .foregroundColor(Color(.common.secondary_light.uiColor))
                HStack {
                    Button {
                        viewModel.handleMinus()
                    } label: {
                        Image(systemName: "minus")
                            .frame(width: 25, height: 25)
                            .background(canDecrement
                                ? Color(.common.background.uiColor)
                                : Color(.common.surface.uiColor)
                            )
                    }
                    .foregroundColor(canDecrement
                        ? Color(.common.secondary_dark.uiColor)
                        : Color(.common.secondary.uiColor)
                    )
                    .cornerRadius(5)
                    .padding(.leading, 5)
                    .disabled(!canDecrement)

                    Text("\(viewModel.shoppingCartInfo.numOfItemsInCart)")
                        .frame(width: 30, alignment: .center)
                        .font(.retailFont(weight: .medium, size: 15.0))

                    Button {
                        viewModel.handlePlus()

                    } label: {
                        Image(systemName: "plus")
                            .frame(width: 25, height: 25)
                            .background(canIncrement
                                ? Color(.common.secondary_dark.uiColor)
                                : Color(.common.secondary.uiColor)
                            )
                    }
                    .foregroundColor(canIncrement
                        ? Color(.common.background.uiColor)
                        : Color(.common.secondary_light.uiColor)
                    )
                    .cornerRadius(5)
                    .padding(.trailing, 5)
                    .disabled(!canIncrement)
                }
            }
            .frame(width: 130, height: 55)
        }
    }
}
