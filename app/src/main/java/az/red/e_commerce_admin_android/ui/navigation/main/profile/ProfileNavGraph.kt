package az.red.e_commerce_admin_android.ui.navigation.main.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.red.e_commerce_admin_android.ui.navigation.main.bottom.BottomNavScreen
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.create_product.components.CreateProduct
import az.red.e_commerce_admin_android.ui.screens.fill_profile.FillProfile

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE_DETAIL,
        startDestination = ProfileNavScreen.CreateProduct.route
    ) {
        composable(ProfileNavScreen.CreateProduct.route) {
            CreateProduct(popBackStack = { navController.popBackStack() })
        }

        composable(ProfileNavScreen.FillProfile.route) {
            FillProfile(
                navigateUp = {
                    navController.navigateUp()
                }, navigateToHome = {
                    navController.navigate(
                        BottomNavScreen.Home.screen_route
                    )
                })
        }
    }
}

