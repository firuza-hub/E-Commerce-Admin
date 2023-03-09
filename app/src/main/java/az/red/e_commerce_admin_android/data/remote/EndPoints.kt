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
    const val PRODUCT = "$PRODUCT_BASE"

    private const val ORDER_BASE = "orders"
    const val UPDATE_ORDER = "${ORDER_BASE}/{id}"
    const val GET_ORDERS = ORDER_BASE
}