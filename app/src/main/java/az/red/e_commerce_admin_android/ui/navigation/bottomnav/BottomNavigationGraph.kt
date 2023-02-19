package az.red.e_commerce_admin_android.ui.navigation.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import az.red.e_commerce_admin_android.ui.screens.bottomnav.cart.CartScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.HomeScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrdersScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
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
            ProfileScreen()
        }
    }
}