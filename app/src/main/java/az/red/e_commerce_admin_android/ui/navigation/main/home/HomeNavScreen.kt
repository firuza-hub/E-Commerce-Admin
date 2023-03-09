package az.red.e_commerce_admin_android.ui.navigation.main.home

sealed class HomeNavScreen(val route: String) {
    object ProductDetails : HomeNavScreen("product_details")
}