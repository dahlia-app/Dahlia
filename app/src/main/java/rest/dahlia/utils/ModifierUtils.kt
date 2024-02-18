package rest.dahlia.utils

import androidx.compose.ui.Modifier

/**
 * Applies a set of modifiers if the [predicate] is true
 *
 * @param predicate Condition for when to apply the new modifiers
 * @param then Where to chain new modifiers
 */
fun Modifier.thenIf(predicate: Boolean, then: Modifier.() -> Modifier) =
    if (predicate) then(Modifier.then()) else this