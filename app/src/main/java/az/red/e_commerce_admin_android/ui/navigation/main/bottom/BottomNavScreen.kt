package az.red.e_commerce_admin_android.ui.navigation.main.bottom

import az.red.e_commerce_admin_android.R

sealed class BottomNavScreen(
    val title: String,
    val icon: Int,
    val screen_route: String
) {
    object Home :
        BottomNavScreen(title = "Home", icon = R.drawable.ic_home, screen_route = "home")

    object Orders :
        BottomNavScreen(title = "Orders", icon = R.drawable.ic_orders, screen_route = "orders")

    object Cart :
        BottomNavScreen(title = "Cart", icon = R.drawable.ic_cart, screen_route = "cart")

    object Profile :
        BottomNavScreen(title = "Profile", icon = R.drawable.ic_profile, screen_route = "profile")
}