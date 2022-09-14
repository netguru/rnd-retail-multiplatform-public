package com.netguru.retail.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.netguru.commondomain.router.MainRouter
import com.netguru.retail.Provider
import com.netguru.retail.android.bottomnav.BottomNavCoordinator
import com.netguru.retail.android.onboarding.OnboardingScreen
import com.netguru.retail.android.productAr.ProductArScreen
import com.netguru.retail.android.productDetails.ProductDetailsScreen
import com.netguru.retail.android.userContent.UserContentScreen
import com.netguru.retail.android.userSingleImage.UserSingleImageScreen

@Composable
fun MainCoordinator(showOnboarding: Boolean = true) {
    val navController = rememberNavController()
    Provider.registerMainRouter(Router(navController))

    NavHost(
        navController,
        if (showOnboarding) MainRoute.ONBOARDING else MainRoute.BOTTOM_NAV
    ) {
        composable(MainRoute.ONBOARDING) {
            OnboardingScreen()
        }
        composable(MainRoute.BOTTOM_NAV) {
            BottomNavCoordinator()
        }
        composable(MainRoute.PRODUCT_DETAILS) {
            ProductDetailsScreen()
        }
        composable(MainRoute.PRODUCT_AR) {
            ProductArScreen()
        }
        composable(MainRoute.USER_CONTENT) {
            UserContentScreen()
        }
        composable(MainRoute.USER_SINGLE_IMAGE) {
            UserSingleImageScreen()
        }
    }
}

class Router(private val navController: NavController) : MainRouter {

    override fun toBottomNav() {
        navController.navigate(MainRoute.BOTTOM_NAV) {
            popUpTo(MainRoute.ONBOARDING) {
                inclusive = true
            }
        }
    }

    override fun toProductDetails() {
        navController.navigate(MainRoute.PRODUCT_DETAILS)
    }

    override fun toProductAr() {
        navController.navigate(MainRoute.PRODUCT_AR)
    }

    override fun toUserContent() {
        navController.navigate(MainRoute.USER_CONTENT)
    }

    override fun toUserSingleImage() {
        navController.navigate(MainRoute.USER_SINGLE_IMAGE)
    }

    override fun back() {
        navController.popBackStack()
    }
}
