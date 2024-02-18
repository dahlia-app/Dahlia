package rest.dahlia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import rest.dahlia.utils.padding
import rest.dahlia.utils.thenIf

/**
 * Represents a container for [VerticalSegmentedButton]s
 */
@Composable
fun VerticalSegmentedButtonContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp),
        content = content,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .then(modifier)
    )
}

/**
 * Button designed to be part of a group where they're separated by a gap
 *
 * @param text The label to use for the button
 * @param onClick Callback for when this button is clicked
 * @param modifier The [Modifier] for this [VerticalSegmentedButton]
 * @param textStyle The [TextStyle] to use for the label
 * @param textColor The color to use for the labels text
 * @param containerColor Background color to use for the container
 * @param rounding The corner radius to apply to this button
 * @param enabled Whether or not this button can be clicked
 * @param padding The padding applied to this button
 */
@Composable
fun VerticalSegmentedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    rounding: Dp = 5.dp,
    enabled: Boolean = true,
    padding: PaddingValues = 16.dp.padding
) {
    Text(
        text = text,
        style = textStyle,
        textAlign = TextAlign.Center,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(rounding))
            .thenIf(!enabled) {
                alpha(0.65f)
            }
            .background(containerColor)
            .clickable(role = Role.Button, enabled = enabled, onClick = onClick)
            .padding(padding)
            .fillMaxWidth()
            .then(modifier)
    )
}