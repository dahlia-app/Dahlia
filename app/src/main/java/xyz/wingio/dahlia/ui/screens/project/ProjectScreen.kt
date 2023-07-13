package xyz.wingio.dahlia.ui.screens.project

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import org.koin.androidx.compose.getViewModel
import xyz.wingio.dahlia.domain.models.Project
import xyz.wingio.dahlia.domain.models.Request
import xyz.wingio.dahlia.domain.models.Variable
import xyz.wingio.dahlia.ui.screens.project.components.ProjectTitleBar
import xyz.wingio.dahlia.ui.viewmodels.project.ProjectViewModel

class ProjectScreen(private val project: Project) : AndroidScreen() {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("MutableCollectionMutableState")
    override fun Content() {
        val viewModel: ProjectViewModel = getViewModel()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

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