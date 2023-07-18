package xyz.wingio.dahlia.domain.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import xyz.wingio.dahlia.domain.dto.Method
import xyz.wingio.dahlia.domain.dto.Request as DtoRequest

class Request(
    method: Method = Method.GET,
    name: String,
    description: String? = null,
    folder: Int = 1,
    url: String = "",
    queries: Map<String, String> = mapOf(),
    headers: Map<String, String> = mapOf(),
    variables: Map<String, Variable> = mapOf(),
    body: String? = null
) {

    constructor(
        req: DtoRequest
    ) : this(
        method = req.method,
        name = req.name,
        description = req.description,
        folder = req.folder,
        url = req.url,
        queries = req.queries,
        headers = req.headers,
        variables = req.variables.mapValues { Variable(it.value) },
        body = req.body
    )

    var method: Method by mutableStateOf(method)

    var name: String by mutableStateOf(name)

    var description by mutableStateOf(description)

    var folder by mutableIntStateOf(folder)

    var url by mutableStateOf(url)

    var queries = mutableStateMapOf<String, String>().apply { putAll(queries) }

    var headers = mutableStateMapOf<String, String>().apply { putAll(headers) }

    var variables = mutableStateMapOf<String, Variable>().apply { putAll(variables) }

    var body by mutableStateOf(body)

}