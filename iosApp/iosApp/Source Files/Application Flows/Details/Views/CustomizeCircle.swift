//
//  CustomizeCircle.swift
//  R&D KMM Retail
//

import SwiftUI

/// Circle reflecting chosen customization option.
struct CustomizeCircle: View {

    /// Hex color of the circle.
    let color: Int64

    /// Boolean value to add or remove circle's stroke.
    var isSelected: Bool

    // MARK: - View body

    var body: some View {

        VStack {
            let strokeColor = isSelected ? Color.black : Color.clear
            VStack {
                Circle()
                    .strokeBorder(Color(.common.background.uiColor), lineWidth: 2)
                    .background(Circle().fill(Color(hex: color)))
                    .frame(width: 17, height: 17)
            }
            .background(
                RoundedRectangle(cornerRadius: 20)
                    .stroke(strokeColor, lineWidth: 1.5)
            )
        }
    }
}

struct CustomizeCircle_Previews: PreviewProvider {
    static var previews: some View {
        CustomizeCircle(color: 0xFF0000, isSelected: false)
    }
}
