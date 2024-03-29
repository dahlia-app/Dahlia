package rest.dahlia.domain.dto

import kotlinx.serialization.Serializable
import rest.dahlia.domain.models.Folder as ModelFolder

@Serializable
data class Folder(
    val id: Int,
    val name: String
) {

    companion object {

        fun fromModel(model: ModelFolder) = with(model) {
            Folder(id, name)
        }

    }

    fun toModel() = ModelFolder(this)

}