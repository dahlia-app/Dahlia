package xyz.wingio.dahlia.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import xyz.wingio.dahlia.domain.manager.PreferenceManager
import xyz.wingio.dahlia.domain.manager.ProjectManager

fun managerModule() = module {

    singleOf(::ProjectManager)
    singleOf(::PreferenceManager)

}