package az.red.e_commerce_admin_android.ui.navigation.main.bottom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.red.e_commerce_admin_android.ui.navigation.main.profile.ProfileNavScreen
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.HomeScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrdersScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bottomNavGraph(navController: NavHostController) {
    navigation(route = Graph.MAIN, startDestination = BottomNavScreen.Home.screen_route) {
        composable(BottomNavScreen.Home.screen_route) {
            HomeScreen(navigateUp = { navController.navigateUp() },
                navigateTo = { navController.navigate(it) })
        }
        composable(BottomNavScreen.Orders.screen_route) {
            OrdersScreen(navigateUp = {navController.navigateUp()}, navigateTo = {navController.navigate(it)})
        }
        composable(BottomNavScreen.Profile.screen_route) {
            ProfileScreen(
                popUpToRoot = {
                    navController.navigate(route = Graph.AUTH) {
                        popUpTo(route = Graph.ROOT)
                    }
                },
                navigateUp = { navController.navigateUp() },
                navigateCreateNewProduct = {
                    navController.navigate(ProfileNavScreen.CreateProduct.route)
                },
                navigateToProfileEdit = {
                    navController.navigate(ProfileNavScreen.FillProfile.route)
                },
                navigationMainGraph = {
                    navController.navigate(route = Graph.MAIN)
                }
            )
        }
    }
}