package xyz.wingio.dahlia.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import xyz.wingio.dahlia.R

@Composable
fun BackButton() {
    val nav = LocalNavigator.currentOrThrow

    if(nav.canPop) {
        IconButton(onClick = { nav.pop() }) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = stringResource(R.string.back))
        }
    }
}