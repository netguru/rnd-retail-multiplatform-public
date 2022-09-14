package com.netguru.commondata

import com.netguru.commondomain.data.ProductDetailsDataSource
import com.netguru.commondomain.product.model.*
import kotlinx.coroutines.delay

@Suppress("MagicNumber", "StringLiteralDuplication")
class ProductDetailsMockDataSource : ProductDetailsDataSource {

    private val placeholderDescription = "Crafted with incredible precision and care, this product" +
        " is the perfect addition to your home. Thanks to the build materials it's very light" +
        " and easily adjustable. Its trendy design will make make your room a welcoming place."

    private val brownBrownConfig = Configuration(
        id = "0",
        fabricVariant = FabricVariant("Brown", 0xFF5A2B0F),
        woodVariant = WoodVariant("Brown", 0xFF3F1703),
        sizeVariant = SizeVariant("30x20x20")
    )
    private val greyBeigeConfig = Configuration(
        id = "1",
        fabricVariant = FabricVariant("Grey", 0xFF7E7670),
        woodVariant = WoodVariant("Beige", 0xFFCBB899),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val greyDarkConfig = Configuration(
        id = "2",
        fabricVariant = FabricVariant("Grey", 0xFF7E7670),
        woodVariant = WoodVariant("Dark", 0xFF664231),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val greyOrangeConfig = Configuration(
        id = "3",
        fabricVariant = FabricVariant("Grey", 0xFF7E7670),
        woodVariant = WoodVariant("Orange", 0xFFB38443),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val blackBeigeConfig = Configuration(
        id = "4",
        fabricVariant = FabricVariant("Black", 0xFF282828),
        woodVariant = WoodVariant("Beige", 0xFFCBB899),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val blackDarkConfig = Configuration(
        id = "5",
        fabricVariant = FabricVariant("Black", 0xFF282828),
        woodVariant = WoodVariant("Dark", 0xFF664231),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val blackOrangeConfig = Configuration(
        id = "6",
        fabricVariant = FabricVariant("Black", 0xFF282828),
        woodVariant = WoodVariant("Orange", 0xFFB38443),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val greenBeigeConfig = Configuration(
        id = "7",
        fabricVariant = FabricVariant("Green", 0xFF3D9667),
        woodVariant = WoodVariant("Beige", 0xFFCBB899),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val greenDarkConfig = Configuration(
        id = "8",
        fabricVariant = FabricVariant("Green", 0xFF3D9667),
        woodVariant = WoodVariant("Dark", 0xFF664231),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val greenOrangeConfig = Configuration(
        id = "9",
        fabricVariant = FabricVariant("Green", 0xFF3D9667),
        woodVariant = WoodVariant("Orange", 0xFFB38443),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val blackGoldConfig = Configuration(
        id = "10",
        fabricVariant = FabricVariant("Black", 0xFF4B4641),
        woodVariant = WoodVariant("Gold", 0xFFE7CA9E),
        sizeVariant = SizeVariant("30x20x40")
    )
    private val blackSilverConfig = Configuration(
        id = "11",
        fabricVariant = FabricVariant("Black", 0xFF2B1B0D),
        woodVariant = WoodVariant("Silver", 0xFFD1D1D1),
        sizeVariant = SizeVariant("30x30x30")
    )
    private val tealGreyConfig = Configuration(
        id = "12",
        fabricVariant = FabricVariant("Teal", 0xFF7B9DA8),
        woodVariant = WoodVariant("Grey", 0xFF9D9D9D),
        sizeVariant = SizeVariant("15x15x25")
    )
    private val whiteWhiteConfig = Configuration(
        id = "13",
        fabricVariant = FabricVariant("White", 0xFFDEDCDA),
        woodVariant = WoodVariant("White", 0xFFDEDCDA),
        sizeVariant = SizeVariant("10x10x12")
    )
    private val greyBeigeLargeConfig = Configuration(
        id = "14",
        fabricVariant = FabricVariant("Grey", 0xFF7E7670),
        woodVariant = WoodVariant("Beige", 0xFFCBB899),
        sizeVariant = SizeVariant("50x30x60")
    )
    private val tealBrownConfig = Configuration(
        id = "12",
        fabricVariant = FabricVariant("Teal", 0xFF7B9DA8),
        woodVariant = WoodVariant("Brown", 0xFF6F391D),
        sizeVariant = SizeVariant("15x15x25")
    )

    private val bartolomeo = ProductDetails(
        productId = "0",
        description = placeholderDescription,
        averageStars = 3.3f,
        numOfReviews = 34,
        defaultConfiguration = greyBeigeConfig,
        possibleConfigurations = listOf(
            greyBeigeConfig, greyDarkConfig, greyOrangeConfig,
            blackBeigeConfig, blackDarkConfig, blackOrangeConfig,
            greenBeigeConfig, greenDarkConfig, greenOrangeConfig,
            greyBeigeLargeConfig
        ),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1VIeVZ12S2UcCW3Vz1RI5UU5S5c3r53-8",
                configurationId = greyBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1YBdwsEYQwyoSjlqOZCppImvCjjii7CbT",
                configurationId = greyBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1mEQcSlrAUT0Q2temI5pmjekLv-u0sw62",
                configurationId = greyBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1KMPuKrwaajJ-CYfK-yCwHropix4oLGDR",
                configurationId = greyDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1q9s1Be2pWb9zbDNrKhDALT4IExKyGQ3P",
                configurationId = greyDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1hI9BzN8gaZzappZDef7kWIXA-JjTY8i8",
                configurationId = greyDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1IXB8NSfTzVkvtqjenzHaZYdCfWXdpI7Y",
                configurationId = greyOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1yB5lTi2pUUvkT3DWkd7hUf_txzKDnFke",
                configurationId = greyOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1P93-qWu6w2OEM3aH6LsjgoFA3zitUIYy",
                configurationId = greyOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1yz5bQFz3QIZmHP0k4necisbUmHwxAj4u",
                configurationId = blackBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1OuAIPCZbzzF4bQ3sA5c1cTcTjAktHJy2",
                configurationId = blackBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1PHc9AhGM-VmbkTFruRFkyjvM5Yvz9SSR",
                configurationId = blackBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1gUJNqTO94MasU3wXssN3p0J1opxf7e1t",
                configurationId = blackDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1_Qn6dYF8ZIW9gij1NE9NxrCx6FxQS4kL",
                configurationId = blackDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1KtbfHgLADvuzRDAqC1uJk4IiOIEY44Np",
                configurationId = blackDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/13y95ievjcSokcYpXrMB3d5yc9WA0Ek2p",
                configurationId = blackOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1aMHKbxIL9Lekj1B0rFV_Cu8B6BwME9qH",
                configurationId = blackOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1FtCCE5vnOdXsFc6v7trAdzZKabyYsMcJ",
                configurationId = blackOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1FLueQSK-5v0e6waVNlY1CTWO5TU3siB6",
                configurationId = greenBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/13qvfCakUUnjEt7kzjXLnzCvPIeDDU4Gw",
                configurationId = greenBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1pCHl2jiBHsGeSqQw08YGE96Qf5IflJ_o",
                configurationId = greenBeigeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1fW_q9CQ7QKVr31jbZq-L38rekAZFJtzb",
                configurationId = greenDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1gJvuX1a-Yne0G6DvErpr81k5Thnv-UWV",
                configurationId = greenDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1wd_fcQQhMBbPf1hos_Gi9xjTmo847RI1",
                configurationId = greenDarkConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1yekTJAfcN39stlTSxEHcdLxFpj5w5WI0",
                configurationId = greenOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1deKawZPEkx_B0FZCHf4Dvwsa97sMwJtP",
                configurationId = greenOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/10Gk4-ncKz4bXCUGUGlVfL5k5VESdk4uy",
                configurationId = greenOrangeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1VIeVZ12S2UcCW3Vz1RI5UU5S5c3r53-8",
                configurationId = greyBeigeLargeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1YBdwsEYQwyoSjlqOZCppImvCjjii7CbT",
                configurationId = greyBeigeLargeConfig.id
            ),
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1mEQcSlrAUT0Q2temI5pmjekLv-u0sw62",
                configurationId = greyBeigeLargeConfig.id
            )
        )
    )

    private val guardian = ProductDetails(
        productId = "1",
        description = placeholderDescription,
        averageStars = 4.7f,
        numOfReviews = 21,
        defaultConfiguration = brownBrownConfig,
        possibleConfigurations = listOf(brownBrownConfig),
        listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1ItuqTikQk9uijOsIMl_SIomOM-xtLpF2",
                configurationId = brownBrownConfig.id
            )
        )
    )

    private val turley = ProductDetails(
        productId = "2",
        description = placeholderDescription,
        averageStars = 1.5f,
        numOfReviews = 89,
        defaultConfiguration = blackGoldConfig,
        possibleConfigurations = listOf(blackGoldConfig),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1-JnsyB0kHpzduEJw2UBXoTDkm4c1c7D3",
                configurationId = blackGoldConfig.id
            )
        )
    )

    private val qwazar = ProductDetails(
        productId = "3",
        description = placeholderDescription,
        averageStars = 2.5f,
        numOfReviews = 154,
        defaultConfiguration = blackSilverConfig,
        possibleConfigurations = listOf(blackSilverConfig),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1rRQBzCdVpQYb5a_lMxuZEPCeeAnMT48I",
                configurationId = blackSilverConfig.id
            )
        )
    )

    private val invincible = ProductDetails(
        productId = "4",
        description = placeholderDescription,
        averageStars = 3.3f,
        numOfReviews = 34,
        defaultConfiguration = greyBeigeConfig,
        possibleConfigurations = listOf(greyBeigeConfig),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1IkG8OJamUA41g85JK1TD3xHX8HmMEyQR",
                configurationId = greyBeigeConfig.id
            )
        )
    )

    private val tulsa = ProductDetails(
        productId = "5",
        description = placeholderDescription,
        averageStars = 3.3f,
        numOfReviews = 34,
        defaultConfiguration = tealGreyConfig,
        possibleConfigurations = listOf(tealGreyConfig),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1B-W_I2pnga6aUmvGrBntTDT1GECNrxH3",
                configurationId = tealGreyConfig.id
            )
        )
    )

    private val henry = ProductDetails(
        productId = "6",
        description = placeholderDescription,
        averageStars = 3.5f,
        numOfReviews = 41,
        defaultConfiguration = brownBrownConfig,
        possibleConfigurations = listOf(brownBrownConfig),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/10dQuScL6FM70XNg1TbmVSfj14j7kk3QQ",
                configurationId = brownBrownConfig.id
            )
        )
    )

    private val royalCouch = ProductDetails(
        productId = "7",
        description = placeholderDescription,
        averageStars = 3.7f,
        numOfReviews = 66,
        defaultConfiguration = tealBrownConfig,
        possibleConfigurations = listOf(tealBrownConfig),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1W08RlSHBo5OdSlt_AbxRsPOkrpuR2bNP",
                configurationId = tealBrownConfig.id
            )
        )
    )

    private val sugar = ProductDetails(
        productId = "8",
        description = placeholderDescription,
        averageStars = 5.0f,
        numOfReviews = 1,
        defaultConfiguration = whiteWhiteConfig,
        possibleConfigurations = listOf(whiteWhiteConfig),
        gallery = listOf(
            GalleryImage(
                imageUrl = "https://lh3.googleusercontent.com/d/144_8XrTiOQpvGDvb6BOLpvLUBABGVGux",
                configurationId = whiteWhiteConfig.id
            )
        )
    )

    private val products = listOf(
        bartolomeo, guardian, turley, qwazar, invincible, tulsa, henry, royalCouch, sugar
    ).associateBy { it.productId }

    override suspend fun getForProduct(productId: String): ProductDetails {
        delay(800)
        return products[productId] ?: throw NoSuchElementException(DETAILS_NOT_FOUND_MESSAGE)
    }

    companion object {
        const val DETAILS_NOT_FOUND_MESSAGE = "Product details not found"
    }
}
