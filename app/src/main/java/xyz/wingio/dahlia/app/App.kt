package xyz.wingio.dahlia.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import xyz.wingio.dahlia.di.managerModule
import xyz.wingio.dahlia.di.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule(),
                managerModule()
            )
        }
    }

}