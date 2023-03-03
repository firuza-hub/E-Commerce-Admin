package az.red.e_commerce_admin_android.ui.navigation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import az.red.e_commerce_admin_android.ui.navigation.auth.authNavGraph
import az.red.e_commerce_admin_android.ui.navigation.main.bottomNavGraph
import az.red.e_commerce_admin_android.ui.screens.create_product.CreateProduct

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(navController, route = Graph.ROOT, startDestination = Graph.AUTH) {
        authNavGraph(navController)
        bottomNavGraph(navController = navController)
        composable(route = BottomNavigationScreens.CREATE_PRODUCT) {
            CreateProduct(navController = navController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val AUTH = "auth_graph"
}

object BottomNavigationScreens {
    const val CREATE_PRODUCT = "create_product"
}