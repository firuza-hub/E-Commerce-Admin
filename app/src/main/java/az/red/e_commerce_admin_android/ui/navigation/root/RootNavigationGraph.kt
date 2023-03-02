package az.red.e_commerce_admin_android.ui.navigation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import az.red.e_commerce_admin_android.ui.navigation.auth.authNavGraph
import az.red.e_commerce_admin_android.ui.navigation.main.bottomNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(navController, route = Graph.ROOT, startDestination = Graph.AUTH) {
        authNavGraph(navController)
        bottomNavGraph(navController = navController)
    }
}

object Graph{
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val AUTH = "auth_graph"
}