package az.red.e_commerce_admin_android.ui.navigation.main.bottom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.cart.CartScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.HomeScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrdersScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bottomNavGraph() {
    navigation(route = Graph.MAIN, startDestination = BottomNavScreen.Home.screen_route) {
        composable(BottomNavScreen.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavScreen.Orders.screen_route) {
            OrdersScreen()
        }
        composable(BottomNavScreen.Cart.screen_route) {
            CartScreen()
        }
        composable(BottomNavScreen.Profile.screen_route) {
            ProfileScreen()
        }
    }
}