package xyz.wingio.dahlia.ui.viewmodels.home

import cafe.adriel.voyager.core.model.ScreenModel
import xyz.wingio.dahlia.domain.manager.ProjectManager

class HomeViewModel(projectManager: ProjectManager) : ScreenModel {

    val projects = projectManager.projects

}