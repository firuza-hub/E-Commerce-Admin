package az.red.e_commerce_admin_android.ui.navigation.main

import az.red.e_commerce_admin_android.R

sealed class BottomNavScreen(val title:String, val icon:Int, val screen_route:String){

    object Home : BottomNavScreen("Home", R.drawable.ic_home,"home")
    object Orders: BottomNavScreen("Orders",R.drawable.ic_orders,"orders")
    object Cart: BottomNavScreen("Cart",R.drawable.ic_cart,"cart")
    object Profile: BottomNavScreen("Profile",R.drawable.ic_profile,"profile")
}

sealed class DetailScreen(val route:String) {
    object FillProfile : DetailScreen("fill_profile")

    object CreateProduct : DetailScreen("create_product")
}