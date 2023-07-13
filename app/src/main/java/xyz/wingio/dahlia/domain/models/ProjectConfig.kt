package xyz.wingio.dahlia.domain.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import xyz.wingio.dahlia.domain.dto.ProjectConfig as DtoProjectConfig

class ProjectConfig(
    config: DtoProjectConfig
) : java.io.Serializable {

    var name by mutableStateOf(config.name)
    var saveResponses by mutableStateOf(config.saveResponses)

}