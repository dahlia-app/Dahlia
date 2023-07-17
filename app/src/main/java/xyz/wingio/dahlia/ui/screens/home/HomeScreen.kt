package xyz.wingio.dahlia.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import xyz.wingio.dahlia.R
import xyz.wingio.dahlia.ui.components.FAB
import xyz.wingio.dahlia.ui.screens.create.CreateScreen
import xyz.wingio.dahlia.ui.screens.home.components.FilePermissionsDialog
import xyz.wingio.dahlia.ui.screens.home.components.NewProjectBottomSheet
import xyz.wingio.dahlia.ui.screens.home.components.ProjectItem
import xyz.wingio.dahlia.ui.screens.project.ProjectScreen
import xyz.wingio.dahlia.ui.screens.settings.SettingsScreen
import xyz.wingio.dahlia.ui.viewmodels.home.HomeViewModel
import xyz.wingio.dahlia.utils.Utils
import xyz.wingio.dahlia.utils.none
import xyz.wingio.dahlia.utils.padding

class HomeScreen : Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
    override fun Content() {

        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val nav = LocalNavigator.currentOrThrow
        val viewModel: HomeViewModel = getScreenModel()
        val projects =
            viewModel.projects.toList().sortedByDescending { (_, project) -> project.lastModified }

        if (viewModel.bottomSheetOpened) {
            NewProjectBottomSheet(
                onDismiss = { viewModel.closeNewProjectSheet() },
                onNewProjectClick = {
                    viewModel.closeNewProjectSheet()
                    nav.push(CreateScreen())
                },
                onNewRequestClick = {
                    viewModel.closeNewProjectSheet()
                }
            )
        }

        FilePermissionsDialog(
            onGranted = { viewModel.projectManager.loadProjects() }
        )

        Scaffold(
            topBar = { TitleBar(scrollBehavior = scrollBehavior) },
            floatingActionButton = { CreateFAB(viewModel) },
            contentWindowInsets = WindowInsets.none,
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { pv ->
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp,
                contentPadding = 16.dp.padding.copy(bottom = Utils.navBarPadding + 16.dp),
                modifier = Modifier
                    .padding(pv)
                    .fillMaxSize()
            ) {
                items(projects.size) {
                    val (id, project) = projects[it]
                    ProjectItem(
                        project = project,
                        onClick = { nav.push(ProjectScreen(id)) }
                    )
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun TitleBar(
        scrollBehavior: TopAppBarScrollBehavior
    ) {
        val nav = LocalNavigator.currentOrThrow

        LargeTopAppBar(
            title = { Text(stringResource(R.string.projects)) },
            scrollBehavior = scrollBehavior,
            actions = {
                IconButton(onClick = { nav.push(SettingsScreen()) }) {
                    Icon(Icons.Outlined.Settings, contentDescription = stringResource(R.string.settings))
                }
            }
        )
    }

    @Composable
    fun CreateFAB(
        viewModel: HomeViewModel
    ) {
        FAB(
            icon = Icons.Outlined.Add,
            contentDescription = stringResource(R.string.create),
            onClick = { viewModel.openNewProjectSheet() }
        )
    }

}