package rest.dahlia.ui.screens.create.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import rest.dahlia.domain.manager.ProjectManager

class CreateViewModel(
    private val projectManager: ProjectManager
) : ScreenModel {

    var name by mutableStateOf("New Project")

    val projectDir get() = projectManager.projectDir

    fun createProject() = projectManager.createProject(name)

}