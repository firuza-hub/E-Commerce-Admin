package az.red.e_commerce_admin_android

import android.app.Application
import android.content.Context
import az.red.e_commerce_admin_android.di.dataModule
import az.red.e_commerce_admin_android.di.presentationModule
import az.red.e_commerce_admin_android.utils.LocalLanguageManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocalLanguageManager().onAttach(base!!))
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {

            properties(
                mapOf("base_url" to "https://mobile.test-danit.com/api/")
            )

            androidContext(this@App)

            val modules = listOf(dataModule, presentationModule)
            modules(modules)

        }
    }
}