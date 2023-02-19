package az.red.e_commerce_admin_android.ui.navigation.auth

sealed class AuthScreens(val route: String) {
    object LoginAuthScreens : AuthScreens("login_screen")
    object RegisterAuthScreens : AuthScreens("register_screen")
}