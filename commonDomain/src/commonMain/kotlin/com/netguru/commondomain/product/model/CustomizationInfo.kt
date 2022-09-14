package com.netguru.commondomain.product.model

data class CustomizationInfo(
    val availableFabrics: List<FabricVariantInfo> = emptyList(),
    val selectedFabric: FabricVariant = FabricVariant(),
    val availableWoods: List<WoodVariantInfo> = emptyList(),
    val selectedWoodName: String = "",
    val availableSizes: List<SizeVariantInfo> = emptyList(),
    val selectedSizeName: String = "",
    val numOfSelectedCustomizations: Int = 0
) {
    companion object {
        val empty: CustomizationInfo
            get() = CustomizationInfo()
    }
}
