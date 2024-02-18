package rest.dahlia.domain.dto

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import rest.dahlia.R
import rest.dahlia.domain.models.Request as ModelRequest
import rest.dahlia.ui.theme.DeleteRed
import rest.dahlia.ui.theme.GetGreen
import rest.dahlia.ui.theme.HeadTeal
import rest.dahlia.ui.theme.PatchYellow
import rest.dahlia.ui.theme.PostBlue
import rest.dahlia.ui.theme.PutOrange

@Serializable
data class Request(
    var method: Method = Method.GET,
    var name: String,
    var description: String? = null,
    var folder: Int = 1,
    var url: String = "",
    var queries: Map<String, String> = mapOf(),
    var headers: Map<String, String> = mapOf(),
    var variables: Map<String, Variable> = mapOf(),
    var body: String? = null
) {

    companion object {

        fun fromModel(model: ModelRequest) = with(model) {
            Request(method, name, description, folder, url, queries, headers, variables.mapValues { Variable.fromModel(it.value) }, body)
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