package com.netguru.retail.android.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.netguru.commonResources.MR
import com.netguru.retail.android.shared.RetailPlaceholder
import com.netguru.retail.android.shared.RetailTopBar
import com.netguru.retail.android.shop.ShopScreen

@Composable
fun BottomNavCoordinator() {
    val navController = rememberNavController()
    val currentEntry by navController.currentBackStackEntryAsState()

    fun openTab(route: String) {
        navController.navigate(route) {
            popUpTo(BottomNavRoute.HOME) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            RetailTopBar(
                navIcon = MR.assets.icons.menu,
                actionIcon = MR.assets.icons.notifications,
                onClickNav = {}
            )
        },
        bottomBar = {
            BottomNavBar(
                currentRoute = currentEntry?.destination?.route ?: "",
                onChangeRoute = ::openTab
            )
        }
    ) { padding ->
        NavHost(navController, BottomNavRoute.HOME, Modifier.padding(padding)) {
            composable(BottomNavRoute.HOME) {
                ShopScreen()
            }
            composable(BottomNavRoute.USER_GENERATED_CONTENT) {
                RetailPlaceholder(text = "User generated content")
            }
            composable(BottomNavRoute.SHOP_CART) {
                RetailPlaceholder(text = "Shop cart")
            }
            composable(BottomNavRoute.USER_PROFILE) {
                RetailPlaceholder(text = "User profile")
            }
        }
    }
}
