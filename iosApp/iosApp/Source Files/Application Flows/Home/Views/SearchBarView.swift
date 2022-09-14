//
//  SearchBarView.swift
//  R&D KMM Retail
//

import SwiftUI

/// A custom view that displays a search bar
struct SearchBarView: View {

    /// Text to be shown when the text field is empty.
    let placeholderText: String

    let text: String

    let onTextChange: ((String) -> Void)?

    var body: some View {
        HStack(spacing: 12) {
            TextField(
                placeholderText,
                text: Binding<String>(
                    get: {
                        self.text
                    },
                    set: {
                        onTextChange?($0)
                    }
                )
            )
            .font(.retailFont(weight: .regular, size: 14))
            .padding(.leading, 12)
            Image(uiImage: .commonAssets.search.image)
                .resizable()
                .frame(width: 24, height: 24)
                .padding(.trailing, 23)
        }
        .frame(height: 50)
        .background(Color(UIColor.common.secondary_light.uiColor))
        .cornerRadius(12.0)
    }
}

struct CustomSearchBarView_Previews: PreviewProvider {
    static var previews: some View {
        SearchBarView(
            placeholderText: "Search",
            text: "",
            onTextChange: { _ in
            }
        )
        .previewLayout(.sizeThatFits)
    }
}
