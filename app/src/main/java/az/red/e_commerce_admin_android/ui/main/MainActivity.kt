package az.red.e_commerce_admin_android.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import az.red.e_commerce_admin_android.ui.navigation.root.RootNavigationGraph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.BottomNavigationContainer
import az.red.e_commerce_admin_android.ui.themes.AppTheme
import az.red.e_commerce_admin_android.ui.themes.darkColors


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
