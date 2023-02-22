package az.red.e_commerce_admin_android.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import az.red.e_commerce_admin_android.data.remote.auth.SessionManager
import az.red.e_commerce_admin_android.ui.navigation.root.RootNavigationGraph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.BottomNavigationContainer
import az.red.e_commerce_admin_android.ui.themes.AppTheme
import az.red.e_commerce_admin_android.ui.themes.darkColors
import az.red.e_commerce_admin_android.ui.themes.lightColors
import org.koin.android.ext.android.inject

import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.SessionManager
import org.koin.android.ext.android.inject

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    private val sessionManager by inject<SessionManager>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeMode = sessionManager.fetchDarkMode() ?: false

        if (!sessionManager.fetchRememberMe()) sessionManager.removeAuthToken()

        setContent {
            AppTheme(darkColors = darkColors(), darkTheme = themeMode) {

                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomNavigationContainer(navController = navController) }) {
                    Box(
                        modifier = Modifier
                            .background(CustomTheme.colors.background)
                            .fillMaxSize()
                    ) {
                        RootNavigationGraph(navController = navController)
                    }
                }
            }
        }
    }

}
