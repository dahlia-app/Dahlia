package xyz.wingio.dahlia.domain.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import com.github.diamondminer88.zip.ZipReader
import com.github.diamondminer88.zip.ZipWriter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.encodeToString
import xyz.wingio.dahlia.utils.Utils
import xyz.wingio.dahlia.utils.projectDir
import xyz.wingio.dahlia.utils.readObject
import java.io.File
import java.util.UUID
import xyz.wingio.dahlia.domain.dto.Folder as DtoFolder
import xyz.wingio.dahlia.domain.dto.ProjectConfig as DtoProjectConfig
import xyz.wingio.dahlia.domain.dto.Request as DtoRequest
import xyz.wingio.dahlia.domain.dto.Variable as DtoVariable

@Stable
class Project(
    @Contextual private val file: File,
    config: ProjectConfig,
    requests: List<Request> = listOf(),
    folders: List<Folder> = listOf(),
    variables: Map<String, Variable> = emptyMap(),
    lastModified: Instant = Clock.System.now()
) {

    var config by mutableStateOf(config)
    val requests = requests.toMutableStateList()
    val folders = folders.toMutableStateList()
    val variables = mutableStateMapOf<String, Variable>().apply { putAll(variables) }
    var lastModified by mutableStateOf(lastModified)

    companion object {

        fun fromFile(file: File): Project {
            val zip = ZipReader(file)
            val config = zip.readObject<DtoProjectConfig>(".config")
            val variables = zip.readObject<Map<String, DtoVariable>>(".vars")
            val requests = mutableListOf<Request>()
            val folders = mutableListOf<Folder>()
            for (entry in zip) {
                when (entry.name.split(".").last()) {
                    "req" -> requests.add(Request(entry.readObject<DtoRequest>()))
                    "folder" -> folders.add(Folder(entry.readObject<DtoFolder>()))
                }
            }
            zip.close()

            return Project(
                file = file,
                config = ProjectConfig(config),
                requests = requests,
                folders = folders,
                variables = variables.mapValues { Variable(it.value) },
                lastModified = Instant.fromEpochMilliseconds(file.lastModified())
            )
        }

        fun new(name: String): Project {
            val config = ProjectConfig(DtoProjectConfig())
            config.name = name
            val proj = Project(File(projectDir, "$name.dlp"), config)
            proj.save()
            return proj
        }

    }

    fun save(): Project {
        val zip = ZipWriter(file)
        zip.writeEntry(".config", Utils.json.encodeToString(DtoProjectConfig.fromModel(config)))
        for (request in requests) {
            zip.writeEntry(
                "${request.hashCode()}-${
                    UUID.randomUUID().toString().split("-")[0]
                }.req", Utils.json.encodeToString(DtoRequest.fromModel(request))
            )
        }
        for (folder in folders) {
            zip.writeEntry(
                "${folder.hashCode()}-${
                    UUID.randomUUID().toString().split("-")[0]
                }.folder", Utils.json.encodeToString(DtoFolder.fromModel(folder))
            )
        }
        zip.writeEntry(
            ".vars",
            Utils.json.encodeToString<Map<String, DtoVariable>>(
                variables.mapValues { DtoVariable.fromModel(it.value) }
            )
        )
        zip.close()
        lastModified = Clock.System.now()
        return this
    }

}