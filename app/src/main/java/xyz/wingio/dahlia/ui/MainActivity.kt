package xyz.wingio.dahlia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import xyz.wingio.dahlia.ui.screens.home.HomeScreen
import xyz.wingio.dahlia.ui.theme.DahliaTheme
import xyz.wingio.dahlia.ui.transitions.SlideTransition


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            DahliaTheme {
                Navigator(HomeScreen()) {
                    SlideTransition(it)
                }
            }
        }
    }

}