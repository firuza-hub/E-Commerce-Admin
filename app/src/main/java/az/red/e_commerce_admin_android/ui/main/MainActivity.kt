package az.red.e_commerce_admin_android.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import az.red.e_commerce_admin_android.ui.navigation.bottomnav.NavigationGraph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.BottomNavigationContainer
import az.red.e_commerce_admin_android.ui.themes.AppTheme
import az.red.e_commerce_admin_android.ui.themes.darkColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(darkColors = darkColors()) {
                MainScreenView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationContainer(navController = navController) }
    ) {
        NavigationGraph(navController = navController)
    }
}
