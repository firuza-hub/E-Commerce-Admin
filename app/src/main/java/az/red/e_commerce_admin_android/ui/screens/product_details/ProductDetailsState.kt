package az.red.e_commerce_admin_android.ui.screens.product_details

data class ProductDetailsState(
    var id: String,
    var categories: String,
    var color: String?,
    var currentPrice: Double,
    var date: String,
    var description: String,
    var enabled: Boolean,
    var fabric: String?,
    var imageUrls: List<String>,
    var itemNo: String,
    var name: String,
    var previousPrice: Double?,
    var quantity: Int,
    var size: String?,
    val discount: Int = 0
) {
    companion object {
        val NULL = ProductDetailsState(
            "",
            "",
            "",
            0.0,
            "",
            "",
            true,
            "",
            emptyList(),
            "",
            "",
            0.0,
            0,
            "",
            0
        )
    }
}

data class SimilarProduct(
    var id: String,
    var categories: String,
    var color: String?,
    var currentPrice: Double,
    var date: String,
    var description: String,
    var enabled: Boolean,
    var fabric: String?,
    var imageUrls: List<String>,
    var itemNo: String,
    var name: String,
    var previousPrice: Double?,
    var quantity: Int,
    var size: String?,
    val discount: Int = 0
) {
    companion object {
        val NULL = SimilarProduct(
            "", "", "", 0.0, "",
            "", true, "", emptyList(), "", "", 0.0, 0, "", 0
        )
    }
}