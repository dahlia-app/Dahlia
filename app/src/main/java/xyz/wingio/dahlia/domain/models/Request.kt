package xyz.wingio.dahlia.domain.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import xyz.wingio.dahlia.domain.dto.Method
import xyz.wingio.dahlia.domain.dto.Request as DtoRequest
import xyz.wingio.dahlia.domain.dto.Variable as DtoVariable

class Request(
    method: Method = Method.GET,
    name: String = "",
    description: String = "",
    folder: Int = 1,
    url: String = "",
    queries: Map<String, String> = mapOf(),
    params: Map<String, String> = mapOf(),
    headers: Map<String, String> = mapOf(),
    variables: Map<String, Variable> = mapOf(),
    body: String = ""
) : java.io.Serializable {

    constructor(
        req: DtoRequest
    ) : this(
        req.method,
        req.name,
        req.description,
        req.folder,
        req.url,
        req.queries,
        req.params,
        req.headers,
        req.variables.mapValues { Variable(it.value) },
        req.body
    )

    var method: Method by mutableStateOf(method)

    var name: String by mutableStateOf(name)

    var description by mutableStateOf(description)

    var folder by mutableIntStateOf(folder)

    var url by mutableStateOf(url)

    var queries = mutableStateMapOf<String, String>().apply { putAll(queries) }

    var params = mutableStateMapOf<String, String>().apply { putAll(params) }

    var headers = mutableStateMapOf<String, String>().apply { putAll(headers) }

    var variables = mutableStateMapOf<String, Variable>().apply { putAll(variables) }

    var body by mutableStateOf(body)

}