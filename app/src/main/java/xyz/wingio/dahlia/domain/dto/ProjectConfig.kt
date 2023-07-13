package xyz.wingio.dahlia.domain.dto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable
import xyz.wingio.dahlia.domain.models.ProjectConfig

@Serializable
data class ProjectConfig(
    val name: String = "",
    val saveResponses: Boolean = true
) {

    companion object {

        fun fromModel(model: ProjectConfig) = with(model) {
            ProjectConfig(name, saveResponses)
        }

    }

    fun toModel() = ProjectConfig(this)

}