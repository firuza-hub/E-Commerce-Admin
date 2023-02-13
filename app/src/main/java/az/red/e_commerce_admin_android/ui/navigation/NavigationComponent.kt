package az.red.e_commerce_admin_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import az.red.e_commerce_admin_android.ui.login.LoginScreen
import az.red.e_commerce_admin_android.ui.register.RegisterScreen

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen()
        }
    }
}