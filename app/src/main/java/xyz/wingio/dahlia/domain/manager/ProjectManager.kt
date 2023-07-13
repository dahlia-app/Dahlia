package xyz.wingio.dahlia.domain.manager

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import xyz.wingio.dahlia.domain.models.Project
import xyz.wingio.dahlia.utils.projectDir
import xyz.wingio.dahlia.utils.showToast

class ProjectManager {

    val projects: MutableMap<String, Project> = mutableStateMapOf()

    private val names: List<String> get() = projects.map { it.value.config.name.lowercase() }
    private val countedNameRX = " \\((\\d+)\\)\$".toRegex()

    private fun loadProjects() {
        if (projectDir.isFile) projectDir.delete()
        if (!projectDir.exists()) projectDir.mkdirs()
        projectDir.listFiles()?.forEach { file ->
            if (file.isDirectory || !file.name.endsWith(".dlp")) return@forEach
            try {
                Project.fromFile(file).let {
                    projects[it.config.name] = it
                }
            } catch (e: Throwable) {
                showToast("Error loading project: ${file.name}")
                Log.e("Dahlia", "Error loading project: ${file.absolutePath}", e)
            }
        }
    }

    fun createProject(name: String): Project {
        return Project.new(checkName(name)).also { projects[it.config.name] = it }
    }

    tailrec fun checkName(name: String): String {
        if (!names.contains(name.lowercase())) return name
        val newName = if (countedNameRX.containsMatchIn(name)) countedNameRX.replace(name) {
            " (${it.groups[1]!!.value.toInt().inc()})"
        } else "$name (1)"
        return checkName(newName)
    }

    init {
        loadProjects()
    }

}