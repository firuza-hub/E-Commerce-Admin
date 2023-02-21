package az.red.e_commerce_admin_android.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import az.red.e_commerce_admin_android.data.remote.auth.SessionManager
import az.red.e_commerce_admin_android.ui.navigation.root.RootNavigationGraph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.BottomNavigationContainer
import az.red.e_commerce_admin_android.ui.themes.AppTheme
import az.red.e_commerce_admin_android.ui.themes.darkColors
import org.koin.java.KoinJavaComponent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val sessionManager: SessionManager by KoinJavaComponent.inject(
                SessionManager::class.java
            )
            if (!sessionManager.fetchRememberMe()) {
                sessionManager.removeAuthToken()
            }
        } catch (ex: java.lang.Exception) {
            Log.e("MAIN_ACTIVITY_START", ex.stackTraceToString())
        }
        setContent {
            AppTheme(darkColors = darkColors()) {

                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationContainer(navController = navController) }
                ) {
                    RootNavigationGraph(navController = navController)
                }
            }
        }
    }

}
