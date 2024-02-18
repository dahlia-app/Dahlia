package rest.dahlia.ui.screens.home.components

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import rest.dahlia.BuildConfig
import rest.dahlia.R

@Composable
fun FilePermissionsDialog(
    onGranted: () -> Unit
) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
        ManageStorageDialog(
            onGranted = onGranted
        )
    } else {
        ExternalStorageDialog(
            onGranted = onGranted
        )
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.R)
private fun ManageStorageDialog(
    onGranted: () -> Unit
) {
    var manageStorageGranted by remember {
        mutableStateOf(Environment.isExternalStorageManager())
    }

    if (!manageStorageGranted) {
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (Environment.isExternalStorageManager()) {
                    manageStorageGranted = true
                    onGranted()
                }
            }

        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Button(
                    onClick = {
                        Intent(
                            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                            Uri.parse("package:${BuildConfig.APPLICATION_ID}")
                        ).let { launcher.launch(it) }
                    }
                ) {
                    Text(stringResource(R.string.action_open_settings))
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.permissions_grant_title),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.permissions_grant_body),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            icon = {
                Icon(imageVector = Icons.Outlined.Folder, contentDescription = null)
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun ExternalStorageDialog(
    onGranted: () -> Unit
) {
    val storagePermState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    ) {
        if (it.all { (_, granted) -> granted }) onGranted()
    }

    if (!storagePermState.allPermissionsGranted) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Button(
                    onClick = storagePermState::launchMultiplePermissionRequest
                ) {
                    Text(stringResource(R.string.action_confirm))
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.permissions_grant_title),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.permissions_grant_body),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            icon = {
                Icon(imageVector = Icons.Outlined.Folder, contentDescription = null)
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}