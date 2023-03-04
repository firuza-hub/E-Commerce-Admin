package az.red.e_commerce_admin_android.ui.navigation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.bottomnav.cart.CartScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.HomeScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrdersScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileScreen
import az.red.e_commerce_admin_android.ui.screens.create_product.CreateProduct
import az.red.e_commerce_admin_android.ui.screens.fill_profile.FillProfile

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bottomNavGraph(navController: NavHostController) {
    navigation(route = Graph.MAIN, startDestination = BottomNavScreen.Home.screen_route) {
        composable(BottomNavScreen.Home.screen_route) {
            HomeScreen(navController)
        }

        composable(BottomNavScreen.Orders.screen_route) {
            OrdersScreen()
        }
        composable(BottomNavScreen.Cart.screen_route) {
            CartScreen()
        }
        composable(BottomNavScreen.Profile.screen_route) {
            ProfileScreen(navController)
        }

        composable(DetailScreen.CreateProduct.route) {
            CreateProduct(navController)
        }

        composable(DetailScreen.FillProfile.route) {
            FillProfile(navController)
        }

    }
}