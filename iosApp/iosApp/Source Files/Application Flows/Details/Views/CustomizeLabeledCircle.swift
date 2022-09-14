//
//  CustomizeLabeledCircle.swift
//  R&D KMM Retail
//

import SwiftUI

/// Circle with text label reflecting chosen customization option.
struct CustomizeLabeledCircle: View {

    /// String to display next to the circle.
    let text: String

    /// Boolean value to add or remove circle's stroke.
    let isSelected: Bool

    // MARK: - View body

    var body: some View {

        let strokeColor = isSelected ? Color.black : Color.clear
        HStack {
            VStack {
                Circle()
                    .strokeBorder(Color(.common.background.uiColor), lineWidth: 2)
                    .background(Circle().fill(Color(.common.secondary.uiColor)))
                    .frame(width: 17, height: 17)
            }
            .background(
                RoundedRectangle(cornerRadius: 20)
                    .stroke(strokeColor, lineWidth: 1.5)
            )
            Text(text)
                .font(.retailFont(weight: .regular, size: 12.0))
                .foregroundColor(Color(.common.secondary.uiColor))
                .lineLimit(1)
        }
    }
}

struct CustomizeLabeledCircle_Previews: PreviewProvider {
    static var previews: some View {
        CustomizeCircle(color: 0xFF0000, isSelected: false)
    }
}
