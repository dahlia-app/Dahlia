package rest.dahlia.domain.dto

import kotlinx.serialization.Serializable
import rest.dahlia.domain.models.ProjectConfig

@Serializable
data class ProjectConfig(
    val name: String,
    val description: String? = null,
    val version: String? = null,
    val saveResponses: Boolean = false,
    val type: Int = 0
) {

    companion object {

        fun fromModel(model: ProjectConfig) = with(model) {
            ProjectConfig(name, description, version, saveResponses, type.ordinal)
        }

    }

    fun toModel() = ProjectConfig(this)

}