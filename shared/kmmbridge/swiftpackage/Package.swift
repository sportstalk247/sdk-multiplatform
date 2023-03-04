// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "Sportstalk247Kit",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "Sportstalk247Kit",
            targets: ["Sportstalk247Kit"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "Sportstalk247Kit",
            path: "./Sportstalk247Kit.xcframework"
        ),
    ]
)
