package xyz.wingio.dahlia.ui.viewmodels.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import xyz.wingio.dahlia.domain.models.Request

class ProjectViewModel : ViewModel() {

    val currentRequest = mutableStateOf(null as Request?)

}