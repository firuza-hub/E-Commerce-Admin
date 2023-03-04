package az.red.e_commerce_admin_android.ui.navigation.main.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.create_product.CreateProduct
import az.red.e_commerce_admin_android.ui.screens.fill_profile.FillProfile

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(route = Graph.PROFILE_DETAIL, startDestination = ProfileNavScreen.CreateProduct.route) {
        composable(ProfileNavScreen.CreateProduct.route) {
            CreateProduct(navController)
        }

        composable(ProfileNavScreen.FillProfile.route) {
            FillProfile(navController)
        }
    }
}

