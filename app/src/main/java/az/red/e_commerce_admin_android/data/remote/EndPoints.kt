package az.red.e_commerce_admin_android.data.remote

object EndPoints {
    private const val USER_BASE = "customers"

    //Auth
    const val LOGIN = "$USER_BASE/login"
    const val REGISTER = USER_BASE
    const val CURRENT_USER = "$USER_BASE/customer"
    const val UPDATE_USER = USER_BASE
    const val UPDATE_USER_PASSWORD = "$USER_BASE/password"

    //Product
    private const val PRODUCT_BASE = "products"
    const val PRODUCTS_FILTERED = "$PRODUCT_BASE/filter"
    const val PRODUCT = PRODUCT_BASE
    const val PRODUCT_SEARCH = "$PRODUCT_BASE/search"
    const val CREATE_PRODUCT = PRODUCT_BASE
    const val UPDATE_PRODUCT = "$PRODUCT_BASE/{id}"

    //Product Filters
    const val CATEGORY = "catalog"
    const val BRAND = "filters/brand"
    const val SIZE = "sizes"
    const val COLOR = "colors"

    //Order
    private const val ORDER_BASE = "orders"
    const val UPDATE_ORDER = "${ORDER_BASE}/{id}"
    const val GET_ORDERS = ORDER_BASE

    //Review
    private const val REVIEW = "comments"
    const val GET_COMMENTS_OF_CUSTOMER = "$REVIEW/product/{productId}"
    
    // Product Filter
    private const val PRODUCT_FILTERS_BASE = "filters"
    const val PRODUCT_FILTERS = PRODUCT_FILTERS_BASE
}