package xyz.wingio.dahlia.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import xyz.wingio.dahlia.R
import xyz.wingio.dahlia.ui.screens.create.CreateScreen

@Composable
fun NewProjectDialog(onCloseRequest: () -> Unit) {

    val btnMod = Modifier.fillMaxWidth()

    val nav = LocalNavigator.currentOrThrow

    AlertDialog(
        onDismissRequest = onCloseRequest,
        confirmButton = {},
        title = { Text(stringResource(R.string.create_dialog_title)) },
        text = {
            Column {
                FilledTonalButton(
                    onClick = {
                        nav.push(CreateScreen())
                        onCloseRequest()
                    },
                    modifier = btnMod
                ) {
                    Text(stringResource(R.string.new_project))
                }
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    modifier = btnMod
                ) {
                    Text(stringResource(R.string.new_request))
                }
            }
        }
    )

}