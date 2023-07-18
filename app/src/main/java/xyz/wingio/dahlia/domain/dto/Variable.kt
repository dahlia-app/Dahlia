package xyz.wingio.dahlia.domain.dto

import kotlinx.serialization.Serializable
import xyz.wingio.dahlia.domain.models.Variable

@Serializable
data class Variable(
    val value: String,
    val sensitive: Boolean = false
) {

    companion object {

        fun fromModel(model: Variable) = with(model) {
            xyz.wingio.dahlia.domain.dto.Variable(value, sensitive)
        }

    }

}