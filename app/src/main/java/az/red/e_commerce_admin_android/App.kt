package az.red.e_commerce_admin_android

import android.app.Application
import az.red.e_commerce_admin_android.di.dataModule
import az.red.e_commerce_admin_android.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

            properties(
                mapOf("base_url" to "Add base url in this")
            )

            androidContext(this@App)

            val modules = listOf(dataModule, presentationModule)
            modules(modules)

        }
    }
}