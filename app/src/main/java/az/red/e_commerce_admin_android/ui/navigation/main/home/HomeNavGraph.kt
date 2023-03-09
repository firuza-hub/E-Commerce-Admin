package az.red.e_commerce_admin_android.ui.navigation.main.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductDetails

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOME_DETAIL,
        startDestination = HomeNavScreen.ProductDetails.route
    ) {
        composable(
            HomeNavScreen.ProductDetails.route + "/{itemNo}",
            arguments = listOf(navArgument("itemNo") {
                type = NavType.StringType
                defaultValue = "-1"
            })
        ) {
            ProductDetails(
                popBackStack = { navController.popBackStack() },
                navigateTo = { navController.navigate(it) })
        }
    }
}
