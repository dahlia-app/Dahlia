package rest.dahlia.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import rest.dahlia.ui.screens.home.HomeScreen
import rest.dahlia.ui.theme.DahliaTheme
import rest.dahlia.ui.transitions.SlideTransition


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