//
//  DetailsBottomBar.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom view  displaying the bottom bar of Details View.
struct DetailsBottomBar: View {

    @StateObject var viewModel: DetailsViewModel

    // MARK: - View body

    var body: some View {
        VStack {
            Spacer()
            HStack {
                DetailsPlusMinusStepper(viewModel: viewModel)
                    .padding([.leading], 25)
                Button(action: {},
                       label: {
                           HStack {
                               Text(String.common.details_add_to_cart.localized)
                                   .frame(alignment: .leading)
                               Divider()
                               Text(viewModel.formattedTotalPrice())
                                   .frame(alignment: .trailing)
                           }
                           .font(.retailFont(weight: .medium, size: 14.0))
                       })
                    .padding(10)
                    .retailMainButtonText(height: 55, cornerRadius: 10)
            }
            .padding(.bottom, 35)
        }
    }
}
