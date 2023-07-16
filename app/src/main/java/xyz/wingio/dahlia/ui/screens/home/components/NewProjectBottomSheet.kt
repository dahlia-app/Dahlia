package xyz.wingio.dahlia.ui.screens.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import xyz.wingio.dahlia.R
import xyz.wingio.dahlia.ui.components.VerticalSegmentedButton
import xyz.wingio.dahlia.ui.components.VerticalSegmentedButtonContainer
import xyz.wingio.dahlia.ui.components.bottomsheet.BottomSheet
import xyz.wingio.dahlia.ui.components.bottomsheet.BottomSheetLayout

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NewProjectBottomSheet(
    onDismiss: () -> Unit,
    onNewProjectClick: () -> Unit,
    onNewRequestClick: () -> Unit
) {
    BottomSheet(onDismiss = onDismiss) {
        BottomSheetLayout(
            title = {
                Text(
                    text = stringResource(R.string.create_dialog_title),
                    textAlign = TextAlign.Center
                )
            }
        ) {
            VerticalSegmentedButtonContainer {
                VerticalSegmentedButton(
                    text = stringResource(R.string.new_project),
                    onClick = onNewProjectClick
                )
                VerticalSegmentedButton(
                    text = stringResource(R.string.new_request),
                    onClick = onNewRequestClick,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}