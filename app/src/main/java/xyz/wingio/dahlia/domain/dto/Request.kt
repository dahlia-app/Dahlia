package xyz.wingio.dahlia.domain.dto

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import xyz.wingio.dahlia.R
import xyz.wingio.dahlia.domain.models.Request as ModelRequest
import xyz.wingio.dahlia.ui.theme.DeleteRed
import xyz.wingio.dahlia.ui.theme.GetGreen
import xyz.wingio.dahlia.ui.theme.HeadTeal
import xyz.wingio.dahlia.ui.theme.PatchYellow
import xyz.wingio.dahlia.ui.theme.PostBlue
import xyz.wingio.dahlia.ui.theme.PutOrange

@Serializable
data class Request(
    var method: Method = Method.GET,
    var name: String = "",
    var description: String = "",
    var folder: Int = 1,
    var url: String = "",
    var queries: Map<String, String> = mapOf(),
    var params: Map<String, String> = mapOf(),
    var headers: Map<String, String> = mapOf(),
    var variables: Map<String, Variable> = mapOf(),
    var body: String = ""
) {

    companion object {

        fun fromModel(model: ModelRequest) = with(model) {
            Request(method, name, description, folder, url, queries, params, headers, variables.mapValues { Variable.fromModel(it.value) }, body)
        }

    }

    fun toModel() = ModelRequest(this)

}

enum class Method(@DrawableRes val icon: Int, val color: Color) {
    GET(R.drawable.ic_get_24, GetGreen),
    POST(R.drawable.ic_post_24, PostBlue),
    PATCH(R.drawable.ic_patch_24, PatchYellow),
    PUT(R.drawable.ic_put_24, PutOrange),
    HEAD(R.drawable.ic_head_24, HeadTeal),
    DELETE(R.drawable.ic_delete_24, DeleteRed)
}