package rest.dahlia.ui.theme

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.get
import org.koin.compose.koinInject
import rest.dahlia.domain.manager.PreferenceManager
import rest.dahlia.domain.manager.Theme

/**
 * Applies theme colors and styles as well as enabling edge-to-edge support
 */
@Composable
@SuppressLint("NewApi")
fun DahliaTheme(content: @Composable () -> Unit) {
    val prefs: PreferenceManager = koinInject()
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

    (LocalContext.current as ComponentActivity).apply {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = colors.scrim.toArgb(),
                darkScrim = colors.scrim.toArgb(),
                detectDarkMode = { darkTheme }
            )
        )
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}