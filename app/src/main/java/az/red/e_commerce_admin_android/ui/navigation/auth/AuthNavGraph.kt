package az.red.e_commerce_admin_android.ui.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.login.LoginScreen
import az.red.e_commerce_admin_android.ui.screens.register.component.RegisterScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.LoginAuthScreen.route
    ) {
        composable(route = AuthScreen.LoginAuthScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(
            route = AuthScreen.RegisterAuthScreen.route
        ) {
            RegisterScreen(navController = navController)
        }
    }
}