package xyz.wingio.dahlia.ui

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import xyz.wingio.dahlia.ui.screens.home.HomeScreen
import xyz.wingio.dahlia.ui.theme.DahliaTheme
import xyz.wingio.dahlia.ui.transitions.SlideTransition


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        checkPermissions()

        setContent {
            DahliaTheme {
                Navigator(HomeScreen()) {
                    SlideTransition(it)
                }
            }
        }
    }

    private fun checkPermissions() {
        val wPerm: String = Manifest.permission.WRITE_EXTERNAL_STORAGE
        val rPerm: String = Manifest.permission.READ_EXTERNAL_STORAGE
        ActivityCompat.requestPermissions(this, arrayOf(rPerm, wPerm), 1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val canOpen = Environment.isExternalStorageManager()
            if (!canOpen) startActivity(Intent(ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
        }
    }

}