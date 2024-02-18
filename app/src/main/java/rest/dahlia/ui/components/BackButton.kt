package rest.dahlia.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import rest.dahlia.R

/**
 * Button that pops the navigation stack, only visible if the nav stack isn't empty
 */
@Composable
fun BackButton() {
    val nav = LocalNavigator.currentOrThrow

    if(nav.canPop) {
        IconButton(onClick = { nav.pop() }) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = stringResource(R.string.back))
        }
    }
}