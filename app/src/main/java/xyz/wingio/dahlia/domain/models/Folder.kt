package xyz.wingio.dahlia.domain.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import xyz.wingio.dahlia.domain.dto.Folder as DtoFolder

class Folder(
    id: Int = 1,
    name: String = ""
) {

    constructor(folder: DtoFolder): this(folder.id, folder.name)

    var id by mutableIntStateOf(id)
    var name by mutableStateOf(name)

}