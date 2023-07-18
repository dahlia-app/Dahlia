package xyz.wingio.dahlia.domain.dto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable
import xyz.wingio.dahlia.domain.models.ProjectConfig

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