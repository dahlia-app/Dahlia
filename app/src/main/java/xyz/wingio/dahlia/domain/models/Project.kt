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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import xyz.wingio.dahlia.utils.SemVer
import xyz.wingio.dahlia.utils.Utils
import xyz.wingio.dahlia.utils.projectDir
import xyz.wingio.dahlia.utils.readObject
import xyz.wingio.dahlia.utils.readString
import java.io.File
import java.util.UUID
import xyz.wingio.dahlia.domain.dto.Folder as DtoFolder
import xyz.wingio.dahlia.domain.dto.ProjectConfig as DtoProjectConfig
import xyz.wingio.dahlia.domain.dto.Request as DtoRequest
import xyz.wingio.dahlia.domain.dto.Variable as DtoVariable

val VALID_VERSIONS = listOf(SemVer(1, 0, 0))

@Stable
class Project(
    val file: File,
    config: ProjectConfig,
    requests: List<Request> = listOf(),
    folders: List<Folder> = listOf(),
    variables: Map<String, Variable> = emptyMap(),
    lastModified: Instant = Clock.System.now(),
    val version: SemVer = SemVer(1, 0, 0)
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
            val version = zip.readString("v") ?: "1.0.0"

            if (!VALID_VERSIONS.contains(SemVer.fromString(version) ?: SemVer(1, 0, 0)))
                throw IllegalStateException("Dahlia project version $version not supported")

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
                lastModified = Instant.fromEpochMilliseconds(file.lastModified()),
                version = SemVer.fromString(version) ?: SemVer(1, 0, 0)
            )
        }

        fun new(name: String, fileName: String): Project {
            val config = ProjectConfig(DtoProjectConfig(name = name))
            val proj = Project(File(projectDir, "$fileName.dlp"), config)
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
        zip.writeEntry("v", version.toString())
        zip.close()
        lastModified = Clock.System.now()
        return this
    }

    enum class Type {
        NORMAL,
        REQUEST
    }

}