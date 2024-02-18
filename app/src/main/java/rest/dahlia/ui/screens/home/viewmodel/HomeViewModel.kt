package rest.dahlia.ui.screens.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import rest.dahlia.domain.manager.ProjectManager

class HomeViewModel(val projectManager: ProjectManager) : ScreenModel {

    val projects = projectManager.projects

    var bottomSheetOpened by mutableStateOf(false)
        private set

    fun openNewProjectSheet() {
        bottomSheetOpened = true
    }

    fun closeNewProjectSheet() {
        bottomSheetOpened = false
    }

}