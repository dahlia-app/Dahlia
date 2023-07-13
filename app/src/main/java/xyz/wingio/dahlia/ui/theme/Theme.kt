package xyz.wingio.dahlia.ui.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.get
import xyz.wingio.dahlia.domain.manager.PreferenceManager
import xyz.wingio.dahlia.domain.manager.Theme

@Composable
@SuppressLint("NewApi")
fun DahliaTheme(content: @Composable () -> Unit) {
    val prefs: PreferenceManager = get()
    val dynamicColor = prefs.dynamicColor
    val darkTheme = when(prefs.theme) {
        Theme.SYSTEM -> isSystemInDarkTheme()
        Theme.DARK -> true
        else -> false
    }
    val colors = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    val systemUiController = rememberSystemUiController()
    val ctx = LocalContext.current as Activity
    systemUiController.apply {
        setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !darkTheme,
        )
        isNavigationBarContrastEnforced = true
    }

    LaunchedEffect(Unit) {
        WindowCompat.setDecorFitsSystemWindows(ctx.window, false)
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}