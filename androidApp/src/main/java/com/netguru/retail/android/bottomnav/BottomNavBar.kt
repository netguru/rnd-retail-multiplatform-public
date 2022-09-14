package com.netguru.retail.android.bottomnav

import android.graphics.drawable.Drawable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.svgResourceToDrawable
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BottomNavBar(currentRoute: String, onChangeRoute: (String) -> Unit) {
    BottomNavigation(
        backgroundColor = colors.background
    ) {
        arrayOf(
            BottomNavItem(
                route = BottomNavRoute.HOME,
                selectedIconDrawable = svgResourceToDrawable(MR.assets.icons.filled_home),
                unselectedIconDrawable = svgResourceToDrawable(MR.assets.icons.outlined_home),
                contentDescription = stringResource(MR.strings.desc_home)
            ),
            BottomNavItem(
                route = BottomNavRoute.USER_GENERATED_CONTENT,
                selectedIconDrawable = svgResourceToDrawable(MR.assets.icons.filled_apps),
                unselectedIconDrawable = svgResourceToDrawable(MR.assets.icons.outlined_apps),
                contentDescription = stringResource(MR.strings.desc_browser)
            ),
            BottomNavItem(
                route = BottomNavRoute.SHOP_CART,
                selectedIconDrawable = svgResourceToDrawable(MR.assets.icons.filled_shoppingbag),
                unselectedIconDrawable = svgResourceToDrawable(MR.assets.icons.outlined_shoppingbag),
                contentDescription = stringResource(MR.strings.desc_cart)
            ),
            BottomNavItem(
                route = BottomNavRoute.USER_PROFILE,
                selectedIconDrawable = svgResourceToDrawable(MR.assets.icons.filled_user),
                unselectedIconDrawable = svgResourceToDrawable(MR.assets.icons.outlined_user),
                contentDescription = stringResource(MR.strings.desc_profile)
            )
        ).forEach {
            val selected = it.route == currentRoute
            val icon = it.getIconDrawable(selected)
            BottomNavigationItem(
                selected = selected,
                onClick = { onChangeRoute(it.route) },
                icon = {
                    Icon(
                        painter = rememberDrawablePainter(icon),
                        contentDescription = it.contentDescription
                    )
                }
            )
        }
    }
}

@Immutable
private data class BottomNavItem(
    val route: String,
    val contentDescription: String,
    private val unselectedIconDrawable: Drawable,
    private val selectedIconDrawable: Drawable
) {

    fun getIconDrawable(selected: Boolean) =
        if (selected) selectedIconDrawable else unselectedIconDrawable
}
