package xyz.wingio.dahlia.domain.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import xyz.wingio.dahlia.domain.dto.ProjectConfig as DtoProjectConfig

class ProjectConfig(
    name: String,
    description: String? = null,
    version: String? = null,
    saveResponses: Boolean,
    type: Project.Type = Project.Type.NORMAL
) {

    constructor(config: DtoProjectConfig): this(
        name = config.name,
        description = config.description,
        version = config.version,
        saveResponses = config.saveResponses,
        type = Project.Type.values()[config.type]
    )

    var name by mutableStateOf(name)
    var description by mutableStateOf(description)
    var version by mutableStateOf(version)
    var saveResponses by mutableStateOf(saveResponses)
    var type by mutableStateOf(type)

}