package xyz.wingio.dahlia.ui.screens.settings.appearance

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import org.koin.androidx.compose.get
import xyz.wingio.dahlia.R
import xyz.wingio.dahlia.domain.manager.PreferenceManager
import xyz.wingio.dahlia.ui.components.BackButton
import xyz.wingio.dahlia.ui.components.settings.SettingsItemChoice
import xyz.wingio.dahlia.ui.components.settings.SettingsSwitch

class AppearanceSettingsScreen: Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val prefs: PreferenceManager = get()
        val ctx = LocalContext.current

        Scaffold(
            topBar = { TitleBar(scrollBehavior = scrollBehavior) }
        ) { pv ->
            Column(
                modifier = Modifier.padding(pv)
            ) {
                SettingsItemChoice(
                    label = stringResource(R.string.settings_appearance_theme),
                    pref = prefs.theme,
                    onPrefChange = {
                        prefs.theme = it
                    },
                    labelFactory = { ctx.getString(it.nameRes) }
                )
                SettingsSwitch(
                    label = stringResource(R.string.settings_appearance_dynamic_colors),
                    secondaryLabel = stringResource(R.string.settings_appearance_dynamic_colors_desc),
                    disabled = Build.VERSION.SDK_INT < Build.VERSION_CODES.S,
                    pref = prefs.dynamicColor,
                    onPrefChange = { prefs.dynamicColor = it }
                )
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TitleBar(
        scrollBehavior: TopAppBarScrollBehavior
    ) {
        LargeTopAppBar(
            title = { Text(stringResource(R.string.settings_appearance_title)) },
            scrollBehavior = scrollBehavior,
            navigationIcon = { BackButton() }
        )
    }

}