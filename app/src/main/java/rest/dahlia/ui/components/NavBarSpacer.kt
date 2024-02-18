package rest.dahlia.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import rest.dahlia.utils.Utils

/**
 * Spacer that takes up the height of the system navbar
 */
@Composable
fun NavBarSpacer() = Spacer(Modifier.height(Utils.navBarPadding))