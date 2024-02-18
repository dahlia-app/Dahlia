package rest.dahlia.domain.dto

import kotlinx.serialization.Serializable
import rest.dahlia.domain.models.Variable

@Serializable
data class Variable(
    val value: String,
    val sensitive: Boolean = false
) {

    companion object {

        fun fromModel(model: Variable) = with(model) {
            rest.dahlia.domain.dto.Variable(value, sensitive)
        }

    }

}