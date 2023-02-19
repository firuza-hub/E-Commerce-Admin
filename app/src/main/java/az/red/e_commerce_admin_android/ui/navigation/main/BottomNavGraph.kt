package az.red.e_commerce_admin_android.ui.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.cart.CartScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.HomeScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrdersScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileScreen

fun NavGraphBuilder.bottomNavGraph(navController: NavHostController) {
    navigation( route = Graph.MAIN, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Orders.screen_route) {
            OrdersScreen()
        }
        composable(BottomNavItem.Cart.screen_route) {
            CartScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen( navigateToRoot = {navController.navigate(route = Graph.ROOT)})
        }
    }
}