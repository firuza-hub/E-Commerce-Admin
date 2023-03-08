package az.red.e_commerce_admin_android.data.remote

object EndPoints {
    private const val USER_BASE = "customers"

    const val LOGIN = "$USER_BASE/login"
    const val REGISTER = USER_BASE
    const val CURRENT_USER = "$USER_BASE/customer"
    const val UPDATE_USER = USER_BASE
    const val UPDATE_USER_PASSWORD = "$USER_BASE/password"

    private const val PRODUCT_BASE = "products"
    const val PRODUCTS_FILTERED = "$PRODUCT_BASE/filter"
    const val CREATE_PRODUCT = PRODUCT_BASE
    const val CATEGORY = "catalog"
    const val BRAND = "filters/brand"
}