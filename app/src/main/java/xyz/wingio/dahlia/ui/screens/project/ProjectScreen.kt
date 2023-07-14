package xyz.wingio.dahlia.ui.screens.project

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.koin.core.parameter.parametersOf
import xyz.wingio.dahlia.ui.screens.project.components.ProjectTitleBar
import xyz.wingio.dahlia.ui.viewmodels.project.ProjectViewModel

class ProjectScreen(private val projectName: String) : Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("MutableCollectionMutableState")
    override fun Content() {
        val viewModel: ProjectViewModel = getScreenModel { parametersOf(projectName) }
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val project = viewModel.project

        Scaffold(
            topBar = {
                ProjectTitleBar(
                    title = project.config.name,
                    scrollBehavior = scrollBehavior
                )
            },
        ) { pv ->
            Column(
                modifier = Modifier.padding(pv)
            ) {

            }
        }
    }

}