package xyz.wingio.dahlia.ui.viewmodels.home

import androidx.lifecycle.ViewModel
import xyz.wingio.dahlia.domain.manager.ProjectManager

class HomeViewModel(projectManager: ProjectManager) : ViewModel() {

    val projects = projectManager.projects

}