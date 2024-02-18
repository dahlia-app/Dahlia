package rest.dahlia

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rest.dahlia.di.managerModule
import rest.dahlia.di.viewModelModule

class Dahlia : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Dahlia)

            modules(
                viewModelModule(),
                managerModule()
            )
        }
    }

}