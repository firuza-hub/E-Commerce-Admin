package az.red.e_commerce_admin_android.ui.navigation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import az.red.e_commerce_admin_android.ui.navigation.auth.authNavGraph
import az.red.e_commerce_admin_android.ui.navigation.main.bottom.bottomNavGraph
import az.red.e_commerce_admin_android.ui.navigation.main.cart.cartNavGraph
import az.red.e_commerce_admin_android.ui.navigation.main.home.homeNavGraph
import az.red.e_commerce_admin_android.ui.navigation.main.orders.ordersNavGraph
import az.red.e_commerce_admin_android.ui.navigation.main.profile.profileNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController = navController)
        bottomNavGraph(navController = navController)
        homeNavGraph(navController = navController)
        ordersNavGraph(navController = navController)
        cartNavGraph(navController = navController)
        profileNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val AUTH = "auth_graph"
    const val HOME_DETAIL = "home_graph"
    const val PROFILE_DETAIL = "profile_graph"
}