package xyz.wingio.dahlia.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import xyz.wingio.dahlia.ui.viewmodels.create.CreateViewModel
import xyz.wingio.dahlia.ui.viewmodels.home.HomeViewModel
import xyz.wingio.dahlia.ui.viewmodels.project.ProjectViewModel

fun viewModelModule() = module {

    factoryOf(::HomeViewModel)
    factoryOf(::CreateViewModel)
    factoryOf(::ProjectViewModel)

}