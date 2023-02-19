package az.red.e_commerce_admin_android.ui.navigation.main

import az.red.e_commerce_admin_android.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Orders: BottomNavItem("Orders",R.drawable.ic_orders,"orders")
    object Cart: BottomNavItem("Cart",R.drawable.ic_cart,"cart")
    object Profile: BottomNavItem("Profile",R.drawable.ic_profile,"profile")
}