package rest.dahlia.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

/**
 * Represents a list where only a single item can be selected
 * and where each item is an entry from the specified enum [E]
 *
 * @param default The default item to select
 * @param labelFactory Lambda used to generate a label for a given enum entry
 * @param onChoiceSelected Callback for when an item is selected
 */
@Composable
inline fun <reified E : Enum<E>> EnumRadioController(
    default: E,
    labelFactory: (E) -> String = { it.toString() },
    crossinline onChoiceSelected: (E) -> Unit
) {
    var choice by remember { mutableStateOf(default) }

    Column(
        modifier = Modifier.selectableGroup()
    ) {
        enumValues<E>().forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = it == choice,
                        role = Role.RadioButton,
                        onClick = {
                            choice = it
                            onChoiceSelected(it)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(labelFactory(it))
                Spacer(Modifier.weight(1f))
                RadioButton(
                    selected = it == choice,
                    onClick = {
                        choice = it
                        onChoiceSelected(it)
                    }
                )
            }
        }
    }
}