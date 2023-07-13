package xyz.wingio.dahlia.ui.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import xyz.wingio.dahlia.R
import xyz.wingio.dahlia.domain.manager.ProjectManager
import xyz.wingio.dahlia.ui.screens.project.ProjectScreen
import xyz.wingio.dahlia.ui.viewmodels.create.CreateViewModel

class CreateScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        val viewModel: CreateViewModel = getViewModel()
        val projectManager: ProjectManager = get()

        val nav = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = { TitleBar(nav) }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = { text -> viewModel.name = text },
                    label = { Text(stringResource(R.string.name)) },
                    placeholder = { Text(stringResource(R.string.new_project)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(
                        R.string.saved_to,
                        "/Dahlia/Projects/${projectManager.checkName(viewModel.name)}.dlp"
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                FilledTonalButton(
                    onClick = {
                        with(viewModel.createProject()) {
                            nav.replace(
                                ProjectScreen(
                                    this
                                )
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = viewModel.name.isNotBlank()
                ) {
                    Text(stringResource(R.string.create))
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TitleBar(nav: Navigator) {
        LargeTopAppBar(
            title = { Text(stringResource(R.string.new_project)) },
            navigationIcon = {
                IconButton(onClick = { nav.pop() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                }
            }
        )
    }

}

