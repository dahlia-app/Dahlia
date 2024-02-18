package rest.dahlia.ui.screens.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import rest.dahlia.R
import rest.dahlia.ui.components.VerticalSegmentedButton
import rest.dahlia.ui.components.VerticalSegmentedButtonContainer
import rest.dahlia.ui.components.bottomsheet.BottomSheet
import rest.dahlia.ui.components.bottomsheet.BottomSheetLayout

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
                    enabled = false,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}