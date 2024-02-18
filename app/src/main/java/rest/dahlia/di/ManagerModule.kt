package rest.dahlia.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import rest.dahlia.domain.manager.PreferenceManager
import rest.dahlia.domain.manager.ProjectManager

fun managerModule() = module {

    singleOf(::ProjectManager)
    singleOf(::PreferenceManager)

}