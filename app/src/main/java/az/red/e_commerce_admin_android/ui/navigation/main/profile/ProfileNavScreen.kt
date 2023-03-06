package az.red.e_commerce_admin_android.ui.navigation.main.profile

sealed class ProfileNavScreen(val route: String) {
    object FillProfile : ProfileNavScreen("fill_profile")

    object CreateProduct : ProfileNavScreen("create_product")
}