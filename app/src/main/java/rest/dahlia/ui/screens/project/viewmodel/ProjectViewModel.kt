package rest.dahlia.ui.screens.project.viewmodel

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import rest.dahlia.domain.manager.ProjectManager
import rest.dahlia.domain.models.Request

class ProjectViewModel(
    projectManager: ProjectManager,
    id: String
) : ScreenModel {

    val project = projectManager.projects[id]!!
    val currentRequest = mutableStateOf(null as Request?)

}