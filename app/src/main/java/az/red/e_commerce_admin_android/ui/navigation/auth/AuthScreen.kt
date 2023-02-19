package az.red.e_commerce_admin_android.ui.navigation.auth

sealed class AuthScreen(val route: String) {
    object LoginAuthScreen : AuthScreen("login_screen")
    object RegisterAuthScreen : AuthScreen("register_screen")
}