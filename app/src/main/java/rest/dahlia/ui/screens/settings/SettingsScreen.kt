package rest.dahlia.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import rest.dahlia.R
import rest.dahlia.ui.components.BackButton
import rest.dahlia.ui.screens.settings.components.SettingsCategory
import rest.dahlia.ui.screens.settings.appearance.AppearanceSettingsScreen

class SettingsScreen : Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold(
            topBar = { TitleBar(scrollBehavior = scrollBehavior) }
        ) { pv ->
            Column(
                modifier = Modifier.padding(pv)
            ) {
                SettingsCategory(
                    icon = Icons.Outlined.ColorLens,
                    text = stringResource(R.string.settings_appearance_title),
                    subtext = stringResource(R.string.settings_appearance_subtitle),
                    destination = { AppearanceSettingsScreen() }
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
            title = { Text(stringResource(R.string.settings)) },
            scrollBehavior = scrollBehavior,
            navigationIcon = { BackButton() }
        )
    }

}