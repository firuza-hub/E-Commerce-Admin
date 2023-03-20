package az.red.e_commerce_admin_android.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.colorspace.ColorModel
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.graphics.toColor
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import az.red.e_commerce_admin_android.ui.navigation.root.RootNavigationGraph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.BottomNavigationContainer
import az.red.e_commerce_admin_android.ui.themes.AppTheme
import org.koin.android.ext.android.inject
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.LocalLanguageManager
import az.red.e_commerce_admin_android.utils.SessionManager

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val sessionManager by inject<SessionManager>()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocalLanguageManager().onAttach(base!!))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!sessionManager.fetchRememberMe()) sessionManager.removeAuthToken()

        setContent {
            var isdark by remember { mutableStateOf(sessionManager.fetchDarkMode()) }
            LaunchedEffect(true) {
                sessionManager.listenDarkMode { isdark = it }
            }

            AppTheme(isDarkTheme = isdark) {
                val view = LocalView.current
                val window = (view.context as Activity).window
                window.statusBarColor = CustomTheme.colors.darkBtnBackground.toArgb()
                window.navigationBarColor = CustomTheme.colors.background.toArgb()
                WindowCompat.getInsetsController(window, view)
                    .isAppearanceLightStatusBars = isdark

                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationContainer(navController = navController)
                    }
                ) {
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
