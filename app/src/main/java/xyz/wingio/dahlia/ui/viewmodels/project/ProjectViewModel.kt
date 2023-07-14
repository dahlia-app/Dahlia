package xyz.wingio.dahlia.ui.viewmodels.project

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import xyz.wingio.dahlia.domain.manager.ProjectManager
import xyz.wingio.dahlia.domain.models.Request

class ProjectViewModel(
    projectManager: ProjectManager,
    id: String
) : ScreenModel {

    val project = projectManager.projects[id]!!
    val currentRequest = mutableStateOf(null as Request?)

}