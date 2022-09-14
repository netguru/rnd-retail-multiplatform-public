//
//  ViewController.swift
//  R&D KMM Retail
//

import UIKit
import SwiftUI

/// A generic ViewController container instance holding the view.
final class ViewController<Content>: UIHostingController<Content> where Content: View {

    /// A default ViewController initializer.
    ///
    /// - Parameters:
    ///   - view: a SwiftUI view.
    required init(view: Content) {
        super.init(rootView: view)
    }

    @available(*, unavailable)
    override required init(rootView: Content) {
        fatalError("init(rootView:) has not been implemented")
    }

    @available(*, unavailable)
    @objc dynamic required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: Lifecycle events

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.navigationBar.isHidden = true
    }
}
