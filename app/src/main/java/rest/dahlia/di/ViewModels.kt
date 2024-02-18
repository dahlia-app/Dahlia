package rest.dahlia.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import rest.dahlia.ui.screens.create.viewmodel.CreateViewModel
import rest.dahlia.ui.screens.home.viewmodel.HomeViewModel
import rest.dahlia.ui.screens.project.viewmodel.ProjectViewModel

fun viewModelModule() = module {

    factoryOf(::HomeViewModel)
    factoryOf(::CreateViewModel)
    factoryOf(::ProjectViewModel)

}