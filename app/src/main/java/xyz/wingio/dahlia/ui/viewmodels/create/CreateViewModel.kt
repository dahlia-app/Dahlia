package xyz.wingio.dahlia.ui.viewmodels.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import xyz.wingio.dahlia.domain.manager.ProjectManager

class CreateViewModel(
    private val projectManager: ProjectManager
) : ScreenModel {

    var name by mutableStateOf("New Project")

    fun createProject() = projectManager.createProject(name)

}