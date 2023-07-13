package xyz.wingio.dahlia.ui.viewmodels.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import xyz.wingio.dahlia.domain.manager.ProjectManager
import xyz.wingio.dahlia.domain.models.Project

class CreateViewModel(
    private val projectManager: ProjectManager
) : ViewModel() {

    var name by mutableStateOf("New Project")

    fun createProject(): Project = projectManager.createProject(name)

}