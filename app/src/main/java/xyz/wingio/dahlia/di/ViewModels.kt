package xyz.wingio.dahlia.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import xyz.wingio.dahlia.ui.viewmodels.create.CreateViewModel
import xyz.wingio.dahlia.ui.viewmodels.home.HomeViewModel
import xyz.wingio.dahlia.ui.viewmodels.project.ProjectViewModel

fun viewModelModule() = module {

    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateViewModel)
    viewModelOf(::ProjectViewModel)

}