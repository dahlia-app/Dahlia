package rest.dahlia.ui.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject
import rest.dahlia.R
import rest.dahlia.domain.manager.ProjectManager
import rest.dahlia.ui.screens.project.ProjectScreen
import rest.dahlia.ui.screens.create.viewmodel.CreateViewModel

class CreateScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: CreateViewModel = getScreenModel()
        val projectManager: ProjectManager = koinInject()
        val nav = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = { TitleBar(nav) }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
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
                        "${viewModel.projectDir}/${projectManager.checkName(viewModel.name)}.dlp"
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                FilledTonalButton(
                    onClick = {
                        viewModel.createProject().let { (id, _) ->
                            nav.replace(ProjectScreen(id))
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = viewModel.name.isNotBlank()
                ) {
                    Text(stringResource(R.string.create))
                }

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = stringResource(R.string.label_or),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(8.dp)
                    )
                }

                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Import from file")
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
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                }
            }
        )
    }

}

