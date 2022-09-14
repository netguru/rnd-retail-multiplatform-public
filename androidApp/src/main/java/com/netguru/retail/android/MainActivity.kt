package com.netguru.retail.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.netguru.commondomain.base.Loading
import com.netguru.retail.android.theme.RetailTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.loading is Loading.InProgress
            }
        }
        setContent {
            RetailTheme {
                MainCoordinator(showOnboarding = mainViewModel.showOnboarding)
            }
        }
    }
}
