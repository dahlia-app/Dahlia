package xyz.wingio.dahlia.domain.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import xyz.wingio.dahlia.domain.dto.Variable

class Variable(
    value: String,
    sensitive: Boolean = false
) {

    constructor(variable: Variable): this(
        value = variable.value,
        sensitive = variable.sensitive
    )

    var value by mutableStateOf(value)
    var sensitive by mutableStateOf(sensitive)

}